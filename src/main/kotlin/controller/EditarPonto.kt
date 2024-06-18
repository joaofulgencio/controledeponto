package controller

import controller.request.EditarPontoRequest
import exceptions.UnprocessableEntityException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import usecase.EditarPontoUseCase

fun Route.editarPontoRoutes(editarPontoUseCase: EditarPontoUseCase) {

    post("/editarPonto") {
        try {
            val editarPontoRequest = call.receive<EditarPontoRequest>()
            editarPontoUseCase.execute(
                editarPontoRequest.email,
                editarPontoRequest.dataDoPonto,
                editarPontoRequest.entrada,
                editarPontoRequest.saida
            )
            call.respond(HttpStatusCode.OK)
        } catch (e: UnprocessableEntityException) {
            call.respond(HttpStatusCode.UnprocessableEntity, e.message ?: "Erro ao processar a entidade.")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e.message ?: "Erro interno no servidor.")
        }
    }
}
