package controller

import controller.request.CadastrarRequest
import exceptions.UnprocessableEntityException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import usecase.CadastrarUseCase

fun Route.cadastroRoutes(cadastrarUseCase: CadastrarUseCase) {

    post("/registrar") {
        try {
            val cadastrarBody = call.receive<CadastrarRequest>()
            cadastrarUseCase.execute(
                cadastrarBody.cpf,
                cadastrarBody.email,
                cadastrarBody.nome,
                cadastrarBody.departamento,
                cadastrarBody.senha
            )
            call.respond(HttpStatusCode.OK)
        } catch (e: UnprocessableEntityException) {
            call.respond(HttpStatusCode.UnprocessableEntity, e.message ?: "Erro ao processar a entidade.")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e.message ?: "Erro interno no servidor.")
        }
    }
}
