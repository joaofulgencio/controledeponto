package database.repository

import com.google.cloud.firestore.Firestore
import domain.Funcionarios
import domain.Relatorio
import extension.await


interface FuncionariosRepository {
    suspend fun execute() : List<Funcionarios>
}
class FireStoreFuncionariosRepository(private val db: Firestore): FuncionariosRepository {

    override suspend fun execute() : List<Funcionarios>  {
       try {
           val funcionariosCollection = db.collection("funcionarios")
           val query = funcionariosCollection.get().await()
           val funcionarios = query.documents.map { document -> document.toObject(Funcionarios::class.java)}
           return funcionarios
       } catch (e: Exception) {
               throw Exception("Error during Firestore operation: ${e.message}")
       }
    }
}
