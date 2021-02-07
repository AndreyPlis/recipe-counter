package com.andreyplis.recipecounter.db

import android.content.*
import android.os.*
import androidx.room.*
import androidx.sqlite.db.*
import com.andreyplis.recipecounter.db.dao.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*

@Database(
    entities = [GoodEntity::class, RecipeEntity::class, RecipeGoodEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipeEntity.RecipeTypeConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getProductDao(): GoodsDao

    abstract fun getRecipesDao(): RecipesDao

    companion object {
        lateinit var instance: ApplicationDatabase

        fun getInstance(context: Context): ApplicationDatabase {
            if (!this::instance.isInitialized)
                instance = Room.databaseBuilder(
                    context,
                    ApplicationDatabase::class.java, "recipes"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        task().execute()
                    }
                }).build()
            return instance
        }

        class task : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                instance.getProductDao().insert(GoodEntity(1, "Сахар", 1, 1, 1.0f))
                instance.getProductDao()
                    .insert(GoodEntity(2, "Мука", 2, 1, 1.0f))
                instance.getProductDao()
                    .insert(GoodEntity(3, "Соль", 3, 1, 1.0f))

                instance.getRecipesDao().insert(RecipeEntity(0, "зефир", Recipe.TYPE.DESERT, 0, 0, 5, 600f))
                instance.getRecipesDao().insert(RecipeEntity(1, "пряная вишня", Recipe.TYPE.CAKE, 2000, 20, 0, 600f))
            }

        }
    }

}