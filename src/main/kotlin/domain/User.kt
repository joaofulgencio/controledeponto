package domain

data class User(
    val cpf: String = "",
    val nome: String = "",
    val matricula: String = "",
    val email: String = "",
    val departamento: String = "",
    val cargo: String = ""
)

