package database.repository

import com.google.cloud.firestore.Firestore
import extension.await


interface EditarPontoRepository {
    suspend fun execute(email: String, dataDoPonto: String, entrada: String, saida: String)
}

class FireStoreEditarPontoRepository(private val db: Firestore) : EditarPontoRepository {

    override suspend fun execute(email: String, dataDoPonto: String, entrada: String, saida: String) {
        try {
            val controlePontosCollection = db.collection("controle_pontos")
            val pontoQuery =
                controlePontosCollection.whereEqualTo("email", email).whereEqualTo("dataDoPonto", dataDoPonto).get()
                    .await()
            if (!pontoQuery.isEmpty) {
                for (document in pontoQuery.documents) {
                    document.reference.update(mapOf("entrada" to entrada, "saida" to saida)).await()
                }
            } else {
                val newDocument = controlePontosCollection.document()
                val baterPonto = hashMapOf(
                    "email" to email,
                    "dataDoPonto" to dataDoPonto,
                    "entrada" to entrada,
                    "saida" to saida
                )
                newDocument.set(baterPonto as Map<String, Any>).await()
            }
        } catch (e: Exception) {
            throw Exception("Error during Firestore operation: ${e.message}")
        }
    }
}
