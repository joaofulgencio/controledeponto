import config.corsModuleConfig
import config.gsonModuleConfig
import config.loadbalancer.setupLoadBalancer
import controller.*
import controller.flow.funcionariosFlowRoutes
import di.DIContainer
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*


fun main() {

    val di = DIContainer()
    val server = embeddedServer(Netty, port = 8080) {
        moduleAndRouting(di)
    }
    setupLoadBalancer()
    server.start(wait = true)
}

private fun Application.moduleAndRouting(di: DIContainer) {
    gsonModuleConfig()
    corsModuleConfig()

    routing {
        loginRoutes(di.loginUseCase)
        loginReactiveRoutes(di.loginReactiveUseCase)
        cadastroRoutes(di.cadastrarUseCase)
        cadastroRoutes(di.cadastrarUseCase)
        baterPontoRoutes(di.baterPontoUseCase)
        editarPontoRoutes(di.editarPontoUseCase)
        relatorioRoutes(di.relatorioUseCase)
        funcionariosRoutes(di.buscarFuncionariosUseCase)
        funcionariosFlowRoutes(di.buscarFuncionariosFlowUseCase)
    }
}

