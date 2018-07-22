package cz.dmn.cpska.data

import cz.dmn.cpska.data.api.CpsHtmlApi
import cz.dmn.cpska.di.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
abstract class DataModule {

    @Module
    companion object {

        @Provides
        @PerApplication
        @JvmStatic
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()

        @Provides
        @PerApplication
        @JvmStatic
        fun provideCpsHtmlApi(client: OkHttpClient): CpsHtmlApi = Retrofit.Builder()
                .baseUrl("https://www.cpska.cz")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(CpsHtmlApi::class.java)
    }
}