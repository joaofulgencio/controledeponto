package database.repository

import com.google.cloud.firestore.Firestore
import exceptions.UnprocessableEntityException
import extension.await
import kotlinx.coroutines.tasks.await


interface BaterPontoRepository {
    suspend fun execute(email: String, dia: String, mes: String, ano:  String, entrada: String, saida: String)
}
class FireStoreBaterPontoRepository(private val db: Firestore): BaterPontoRepository {

    override suspend fun execute(email: String, dia: String, mes: String, ano:  String, entrada: String, saida: String) {
       try {
           val controlePontosCollection = db.collection("controle_pontos").document()
           val baterPonto = hashMapOf(
               "email" to email,
               "dia" to dia,
               "mes" to mes,
               "ano" to ano,
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
