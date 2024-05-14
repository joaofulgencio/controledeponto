package usecase

import database.repository.BaterPontoRepository
import database.repository.RelatorioRepository
import domain.Relatorio
import java.time.Instant

class RelatorioUseCase(val relatorioRepository: RelatorioRepository) {

    suspend fun execute (email: String) : List<Relatorio> {
        return relatorioRepository.execute(email)
    }
}