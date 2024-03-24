    import controller.loginRoutes
    import io.ktor.server.engine.*
    import io.ktor.server.netty.*
    import config.module
    import io.ktor.server.application.*
    import io.ktor.server.routing.routing


    fun main() {
        val server = embeddedServer(Netty, port = 8080) {
            moduleAndRouting()
        }
        server.start(wait = true)
    }

    private fun Application.moduleAndRouting() {
        module()

        routing {
            loginRoutes()
        }
    }