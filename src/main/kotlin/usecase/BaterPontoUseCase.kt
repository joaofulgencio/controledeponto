package usecase

import database.repository.BaterPontoRepository
import java.time.Instant

class BaterPontoUseCase(val baterPontoRepository: BaterPontoRepository) {

    suspend fun execute (email: String, dia: String, mes: String, ano: String, entrada: String, saida: String) {
        baterPontoRepository.execute(email, dia, mes, ano, entrada, saida)
    }
}