package usecase

import controller.response.LoginResponse
import database.repository.LoginRepository

class LoginUseCase(val loginRepository: LoginRepository) {

    fun execute(matricula: String, senha: String) : LoginResponse {
        val user = loginRepository.findByMatriculaAndPassword(matricula, senha)
        return if (user != null) {
            LoginResponse(true, user.matricula, "Login efetuado com sucesso")
        } else {
            LoginResponse(false, matricula, "Matrícula ou senha inválidos")
        }

    }
}