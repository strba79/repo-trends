package rs.strba.repo.presentation.dependencyinjection.modules

import android.R
import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.strba.repo.Constants
import rs.strba.repo.networking.GitHubApi
import javax.inject.Singleton
import javax.net.ssl.SSLContext


@Module
class NetModule {

    @Singleton
    @Provides
    fun loggingInterceptor() = HttpLoggingInterceptor()


    @Singleton
    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun repoJsonApi(okHttpClient: OkHttpClient): GitHubApi =
        retrofit(okHttpClient).create(GitHubApi::class.java)

}