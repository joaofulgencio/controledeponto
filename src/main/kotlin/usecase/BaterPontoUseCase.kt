package usecase

import database.repository.BaterPontoRepository
import java.time.Instant

class BaterPontoUseCase(val baterPontoRepository: BaterPontoRepository) {

    suspend fun execute (email: String, dataDoPonto: String, entrada: String, saida: String) {
        baterPontoRepository.execute(email, dataDoPonto, entrada, saida)
    }
}