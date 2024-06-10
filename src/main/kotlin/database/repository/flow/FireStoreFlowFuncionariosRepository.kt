package database.repository.flow

import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.ListenerRegistration
import domain.Funcionarios
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface FuncionariosFlowRepository {
     fun startFlow(): Flow<List<Funcionarios>>
}
class FireStoreFlowFuncionariosRepository(private val db: Firestore) : FuncionariosFlowRepository {
    override fun startFlow(): Flow<List<Funcionarios>> = callbackFlow {
        val listenerRegistration: ListenerRegistration =
            db.collection("funcionarios").whereEqualTo("tipo", 1).addSnapshotListener { value, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val funcionarios = value?.documents?.map { document ->
                    document.toObject(Funcionarios::class.java)
                }

                if ( funcionarios != null) {
                    trySend(funcionarios).isSuccess
                }
            }
        awaitClose{ listenerRegistration.remove() }
    }
}