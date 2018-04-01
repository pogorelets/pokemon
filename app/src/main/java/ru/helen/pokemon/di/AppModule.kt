package ru.helen.pokemon.di

import android.content.Context
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import ru.helen.pokemon.repository.*
import ru.helen.pokemon.repository.localbd.DBHelper
import java.util.concurrent.TimeUnit


/**
 * AppModule
 */

@Module
class AppModule(var context: Context) {
    private val BASE_URL = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(600, TimeUnit.SECONDS)
                .connectTimeout(600, TimeUnit.SECONDS)
                .build()



    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providePokemonAPI(retrofit: Retrofit): PokemonAPI = retrofit.create(PokemonAPI::class.java)


    @Provides
    @Singleton
    fun provideNetworkRepository(pokemonAPI: PokemonAPI): NetworkRepository = NetworkRepositoryImpl(pokemonAPI)

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideDBHelper(context: Context) : DBHelper = DBHelper(context)

    @Provides
    @Singleton
    fun provideLocalDBRepository(dbHelper: DBHelper): LocalBDRepository = LocalBDRepositoryImpl(dbHelper)

}