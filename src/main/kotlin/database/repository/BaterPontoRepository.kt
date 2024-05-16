package database.repository

import com.google.cloud.firestore.Firestore
import exceptions.UnprocessableEntityException
import extension.await
import kotlinx.coroutines.tasks.await


interface BaterPontoRepository {
    suspend fun execute(email: String, dataDoPonto: String, entrada: String, saida: String)
}
class FireStoreBaterPontoRepository(private val db: Firestore): BaterPontoRepository {

    override suspend fun execute(email: String, dataDoPonto: String, entrada: String, saida: String) {
       try {
           val controlePontosCollection = db.collection("controle_pontos").document()
           val baterPonto = hashMapOf(
               "email" to email,
               "dataDoPonto" to dataDoPonto,
               "entrada" to entrada,
               "saida" to saida
               )

           val result = controlePontosCollection.create(baterPonto as Map<String, String>).await()
           println("Write result: ${result.updateTime}")
       } catch (e: Exception) {
               throw Exception("Error during Firestore operation: ${e.message}")
       }
    }
}
