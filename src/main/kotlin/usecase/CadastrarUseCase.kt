package usecase

import database.repository.CadastrarRepository

class CadastrarUseCase(val cadastrarRepository: CadastrarRepository) {

    suspend fun execute (cpf: String, email: String, nome: String, departamento: String, senha: String, tipo: Int) {
        cadastrarRepository.execute(cpf, email, nome, departamento, senha, tipo!!)
    }
}