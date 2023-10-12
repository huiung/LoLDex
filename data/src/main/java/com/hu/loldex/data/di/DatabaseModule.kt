package com.hu.loldex.data.di

import android.content.Context
import androidx.room.Room
import com.hu.loldex.data.database.Converters
import com.hu.loldex.data.database.LoLDexDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 * Designed and developed by 2023 huiung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Singleton
    @Provides
    fun provideLoLDexDatabase(@ApplicationContext context: Context, converters: Converters): LoLDexDatabase =
        Room.databaseBuilder(context, LoLDexDatabase::class.java, "LoLDex.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(converters)
            .build()

    @Singleton
    @Provides
    fun provideChampionDao(database: LoLDexDatabase) = database.championDao()

    @Provides
    @Singleton
    fun provideTypeConverter(moshi: Moshi): Converters {
        return Converters(moshi)
    }
}