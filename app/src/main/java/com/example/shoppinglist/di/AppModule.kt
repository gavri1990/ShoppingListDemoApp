package com.example.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.example.shoppinglist.data.db.ShoppingListDao
import com.example.shoppinglist.data.db.ShoppingListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)   //it will live as long as the app does
object AppModule {

    @Provides
    @Singleton  //will create one instance only per module lifetime
    fun provideDatabase(@ApplicationContext context: Context): ShoppingListDatabase{  //safe, leak-free context to build the database
        return Room.databaseBuilder(
                context = context.applicationContext,
                klass = ShoppingListDatabase::class.java,
                name = "ShoppingListDb.db"
            ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseDao(db: ShoppingListDatabase): ShoppingListDao{
        return db.getShoppingListDao()
    }
}