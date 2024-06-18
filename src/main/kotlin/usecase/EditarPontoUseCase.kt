package usecase

import database.repository.EditarPontoRepository

class EditarPontoUseCase(val editarPontoRepository: EditarPontoRepository) {

    suspend fun execute(email: String, dataDoPonto: String, entrada: String, saida: String) {
        editarPontoRepository.execute(email, dataDoPonto, entrada, saida)
    }
}