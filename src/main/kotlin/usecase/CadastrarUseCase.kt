package usecase

import controller.response.LoginResponse
import database.repository.CadastrarRepository
import database.repository.LoginRepository

class CadastrarUseCase(val cadastrarRepository: CadastrarRepository) {

    suspend fun execute (cpf: String, email: String, nome: String, departamento: String, senha: String) {
        cadastrarRepository.execute(cpf, email, nome, departamento, senha)
    }
}