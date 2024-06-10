package database.repository

import domain.User
import com.google.cloud.firestore.Firestore
import extension.await

interface LoginReactiveRepository {
    suspend fun execute(matricula: String, password: String): User?
}
class FireStoreLoginReactiveRepository(private val db: Firestore): LoginReactiveRepository {
    override suspend fun execute(matricula: String, password: String) : User? {
        val funcionariosCollection = db.collection("funcionarios")
        val query = funcionariosCollection
            .whereEqualTo("email", matricula)
            .whereEqualTo("senha", password)
            .get()
            .await()

        return if (!query.isEmpty) {
            val funcionario = query.documents.firstOrNull()
            funcionario?.toObject(User::class.java)
        } else {
            null
        }
    }
}
