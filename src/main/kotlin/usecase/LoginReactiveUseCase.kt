package usecase

import controller.response.LoginResponse
import database.repository.LoginReactiveRepository
import database.repository.LoginRepository

class LoginReactiveUseCase(val loginRepository: LoginReactiveRepository) {

    suspend fun execute(matricula: String, senha: String) : LoginResponse {
        val user = loginRepository.execute(matricula, senha)
        return if (user != null) {
            LoginResponse(true, user.email, "Login efetuado com sucesso", user.tipo)
        } else {
            LoginResponse(false, matricula, "Matrícula ou senha inválidos", -1)
        }

    }
}