package usecase

import database.repository.RelatorioRepository
import domain.Relatorio

class RelatorioUseCase(val relatorioRepository: RelatorioRepository) {

    suspend fun execute (email: String) : List<Relatorio> {
        return relatorioRepository.execute(email)
    }
}