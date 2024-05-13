package di

import config.firebase.firebaseInitializer
import database.repository.CadastrarRepository
import database.repository.FireStoreCadastrarRepository
import database.repository.FireStoreLoginRepository
import database.repository.LoginRepository
import usecase.CadastrarUseCase
import usecase.LoginUseCase

class DIContainer {
    private val fireStore = firebaseInitializer()

    val loginRepository: LoginRepository by lazy {
        FireStoreLoginRepository(fireStore)
    }

    val cadastrarRepository: CadastrarRepository by lazy {
        FireStoreCadastrarRepository(fireStore)
    }

    val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(loginRepository)
    }

    val cadastrarUseCase: CadastrarUseCase by lazy {
        CadastrarUseCase(cadastrarRepository)
    }
}