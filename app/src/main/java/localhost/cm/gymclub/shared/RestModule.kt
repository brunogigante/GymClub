package localhost.cm.gymclub.shared

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import localhost.cm.gymclub.data.repository.AuthRepository
import localhost.cm.gymclub.data.service.AuthService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestModule {
    @Singleton
    @Provides
    fun authService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun authRepository(apiService: AuthService): AuthRepository = AuthRepository(apiService)
}