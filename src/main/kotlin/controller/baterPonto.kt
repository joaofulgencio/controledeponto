package controller

import controller.request.BaterPontoRequest
import controller.request.CadastrarRequest
import exceptions.UnprocessableEntityException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import usecase.BaterPontoUseCase
import usecase.CadastrarUseCase

fun Route.baterPontoRoutes(baterPontoUseCase: BaterPontoUseCase) {

    post("/baterPonto") {
        try {
            val baterPontoBody = call.receive<BaterPontoRequest>()
            baterPontoUseCase.execute(
                baterPontoBody.email,
                baterPontoBody.dia,
                baterPontoBody.mes,
                baterPontoBody.ano,
                baterPontoBody.entrada,
                baterPontoBody.saida
            )
            call.respond(HttpStatusCode.OK)
        } catch (e: UnprocessableEntityException) {
            call.respond(HttpStatusCode.UnprocessableEntity, e.message ?: "Erro ao processar a entidade.")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e.message ?: "Erro interno no servidor.")
        }
    }
}
