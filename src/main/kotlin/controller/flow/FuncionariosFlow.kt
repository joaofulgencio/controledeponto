package controller.flow

import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import usecase.flow.BuscarFuncionariosFlowUseCase

fun Route.funcionariosFlowRoutes(buscarFuncionariosFlowUseCase: BuscarFuncionariosFlowUseCase) {
    get("/funcionarios/{funcionario_tipo}/realtime") {
        val funcionario_tipo =
            call.parameters["funcionario_tipo"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)

        try {
            buscarFuncionariosFlowUseCase.validateTipo(funcionario_tipo)
            call.respondTextWriter(contentType = ContentType.Text.EventStream) {
                val flow = buscarFuncionariosFlowUseCase.startFlow(funcionario_tipo)
                flow.collect { funcionarios ->
                    val jsonData = Gson().toJson(funcionarios)
                    write("data: $jsonData\n\n")
                    flush()
                }
            }
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.Forbidden, e.message ?: "Tipo de funcionário inválido")
        }
    }
}
