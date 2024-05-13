package database.repository

import domain.User
import com.google.cloud.firestore.Firestore

interface LoginRepository {
    fun findByMatriculaAndPassword(matricula: String, password: String): User?
}
class FireStoreLoginRepository(private val db: Firestore): LoginRepository {
    override fun findByMatriculaAndPassword(matricula: String, password: String) : User? {
        val funcionariosCollection = db.collection("funcionarios")

        val query = funcionariosCollection.
        whereEqualTo("email", matricula)
            .whereEqualTo("senha", password)
            .get()
            .get()

        return if (!query.isEmpty) {
            val funcionario = query.documents.firstOrNull()
            funcionario?.toObject(User::class.java)
        } else {
            null
        }
    }
}
