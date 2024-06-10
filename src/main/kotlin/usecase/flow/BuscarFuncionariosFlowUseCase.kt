package usecase.flow

import database.repository.flow.FuncionariosFlowRepository
import domain.Funcionarios
import kotlinx.coroutines.flow.Flow

class BuscarFuncionariosFlowUseCase(val funcionariosFlowRepository: FuncionariosFlowRepository) {

    fun startFlow(tipo: Int): Flow<List<Funcionarios>> {

        return funcionariosFlowRepository.startFlow()
    }

    fun validateTipo(tipo: Int) {
        if (tipo != 0) {
            throw IllegalArgumentException("Tipo de funcionário inválido")
        }
    }
}