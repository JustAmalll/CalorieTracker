package dev.amal.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.tracker_data.repository.TrackerRepositoryImpl
import dev.amal.tracker_data.source.cache.TrackerDatabase
import dev.amal.tracker_data.source.remote.OpenFoodApi
import dev.amal.tracker_domain.repository.TrackerRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): OpenFoodApi = Retrofit.Builder()
        .baseUrl(OpenFoodApi.BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .build()
        .create()

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): TrackerDatabase =
        Room.databaseBuilder(app, TrackerDatabase::class.java, "tracker_db").build()

    @Provides
    @Singleton
    fun provideTrackerRepository(api: OpenFoodApi, db: TrackerDatabase): TrackerRepository =
        TrackerRepositoryImpl(dao = db.dao, api = api)
}