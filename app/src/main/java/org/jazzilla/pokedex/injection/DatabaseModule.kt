package org.jazzilla.pokedex.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.jazzilla.pokedex.data.AppDatabase
import org.jazzilla.pokedex.data.dao.PokemonDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "pokedex-database")
            .createFromAsset("database/pokedex.db")
            .build()

    @Provides
    fun providePokemonDao(database: AppDatabase): PokemonDao = database.pokemonDao()
}