package database.repository

import com.google.cloud.firestore.Firestore
import exceptions.UnprocessableEntityException
import extension.await
import kotlinx.coroutines.tasks.await


interface CadastrarRepository {
    suspend fun execute(cpf: String, email: String, nome: String, departamento: String, senha: String)
}
class FireStoreCadastrarRepository(private val db: Firestore): CadastrarRepository {

    override suspend fun execute(cpf: String, email: String, nome: String, departamento: String, senha: String) {
       try {
           val documentId = "$cpf-$email"
           val funcionariosCollection = db.collection("funcionarios").document(documentId)
           val user = hashMapOf(
               "cpf" to cpf,
               "email" to email,
               "nome" to nome,
               "departamento" to departamento,
               "senha" to senha,

               )

           val result = funcionariosCollection.create(user as Map<String, String>).await()
           println("Write result: ${result.updateTime}")
       } catch (e: Exception) {
           if (e.message?.contains("ALREADY_EXISTS") == true) {
               throw UnprocessableEntityException("Usuário com CPF ou Email fornecido já existe.")
           } else {
               throw Exception("Error during Firestore operation: ${e.message}")
           }
       }
    }
}
