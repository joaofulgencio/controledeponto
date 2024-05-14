package database.repository

import com.google.cloud.firestore.Firestore
import domain.Relatorio
import domain.User
import exceptions.UnprocessableEntityException
import extension.await
import kotlinx.coroutines.tasks.await


interface RelatorioRepository {
    suspend fun execute(email: String) : List<Relatorio>
}
class FireStoreRelatorioRepository(private val db: Firestore): RelatorioRepository {

    override suspend fun execute(email: String) : List<Relatorio>  {
       try {
           val controlePontosCollection = db.collection("controle_pontos")
           val query = controlePontosCollection
               .whereEqualTo("email", email)
               .get()
               .await()

           return if (!query.isEmpty) {
               query.documents.mapNotNull { it.toObject(Relatorio::class.java) }
           } else {
               emptyList()
           }
       } catch (e: Exception) {
               throw Exception("Error during Firestore operation: ${e.message}")
       }
    }
}
