package localhost.cm.gymclub.data.repository

import localhost.cm.gymclub.data.entity.request.LoginRequest
import localhost.cm.gymclub.data.entity.response.UserAuthResponse
import localhost.cm.gymclub.data.exception.WrongCredentialsException
import localhost.cm.gymclub.data.service.AuthService

class AuthRepository(private val authService: AuthService) {
    suspend fun authenticate(email: String, password: String): UserAuthResponse {
        val response = authService.login(LoginRequest(email, password))
        if (response.code() == 403) {
            throw WrongCredentialsException()
        }

        return response.body() ?: throw UnknownError()
    }

}