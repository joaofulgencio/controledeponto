package usecase

import database.repository.FuncionariosRepository
import domain.Funcionarios
import domain.Relatorio

class BuscarFuncionariosUseCase(val relatorioRepository: FuncionariosRepository) {

    suspend fun execute () : List<Funcionarios> {
        return relatorioRepository.execute()
    }
}