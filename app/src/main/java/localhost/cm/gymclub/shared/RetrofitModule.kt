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
            request.newBuilder()
                .addHeader("Authorization", "Bearer ${authSharedPref.jwt}")
                .build()
            chain.proceed(request)
        }

    }

    private val httpClientInterceptor
        get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideOkhttpClient(bearerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(Duration.ofSeconds(15))
            .readTimeout(Duration.ofSeconds(15))
            .addInterceptor(httpClientInterceptor)
            .addInterceptor(bearerInterceptor)
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