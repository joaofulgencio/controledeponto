    import controller.loginRoutes
    import io.ktor.application.*
    import io.ktor.features.*
    import io.ktor.routing.*
    import io.ktor.server.engine.*
    import io.ktor.server.netty.*
    import config.module


    fun main() {
        val server = embeddedServer(Netty, port = 8080) {
            install(DefaultHeaders)
            install(CallLogging)

            module()

            routing {
                loginRoutes()
            }
        }
        server.start(wait = true)
    }