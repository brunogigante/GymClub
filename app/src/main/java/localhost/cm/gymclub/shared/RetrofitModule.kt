package localhost.cm.gymclub.shared

import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideBearerInterceptor(authSharedPref: AuthSharedPref): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
            if (authSharedPref.jwt != null) {
                newRequest.addHeader("Authorization", "Bearer ${authSharedPref.jwt}")
            }
            chain.proceed(newRequest.build())
        }

    }

    private val httpClientInterceptor
        get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideOkhttpClient(
        bearerInterceptor: Interceptor,
        authSharedPref: AuthSharedPref,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(Duration.ofSeconds(15))
            .readTimeout(Duration.ofSeconds(15))
            .addInterceptor(httpClientInterceptor)
            .addInterceptor(bearerInterceptor)
            .addInterceptor {
                val response = it.proceed(it.request())
                if (response.code == 401) {
                    authSharedPref.destroy()
                }
                response
            }
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun retrofitProvider(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}