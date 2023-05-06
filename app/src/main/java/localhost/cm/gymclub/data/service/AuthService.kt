package localhost.cm.gymclub.data.service

import localhost.cm.gymclub.data.entity.request.LoginRequest
import localhost.cm.gymclub.data.entity.response.UserAuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<UserAuthResponse>
}