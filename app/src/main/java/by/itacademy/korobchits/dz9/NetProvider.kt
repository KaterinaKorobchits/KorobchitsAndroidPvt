package by.itacademy.korobchits.dz9

import by.itacademy.korobchits.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetProvider {

    private var api: Api? = null

    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    fun provideOkHttp(): OkHttpClient {

        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            okHttpBuilder.addInterceptor(logging)
        }

        val okHttpClient = okHttpBuilder.build()

        return okHttpClient
    }

    fun provideRetrofit(baseUr: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUr)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

    fun provideApi(retrofit: Retrofit): Api {
        if (api == null)
            api = retrofit.create<Api>(Api::class.java)
        return api!!
    }
}