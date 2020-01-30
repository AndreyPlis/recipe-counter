package com.andreyplis.recipecounter.db

import android.content.*
import android.os.*
import androidx.room.*
import androidx.sqlite.db.*
import com.andreyplis.recipecounter.db.dao.*
import com.andreyplis.recipecounter.db.entity.*

@Database(
    entities = [ProductEntity::class, MeasureEntity::class, RecipeEntity::class, RecipeProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipeEntity.RecipeTypeConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductsDao

    abstract fun getMeasureDao(): MeasuresDao

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
                instance.getMeasureDao().insert(MeasureEntity(1, "кг"))
                instance.getMeasureDao().insert(MeasureEntity(2, "гр"))
                instance.getMeasureDao().insert(MeasureEntity(3, "шт"))
                instance.getMeasureDao().insert(MeasureEntity(4, "л"))
                instance.getMeasureDao().insert(MeasureEntity(5, "мл"))



                instance.getProductDao().insert(ProductEntity(1, "lol", 1, 1, 1.0f))
                instance.getProductDao()
                    .insert(ProductEntity(2, "lol2", 2, 1, 1.0f))
                instance.getProductDao()
                    .insert(ProductEntity(3, "lol3", 3, 1, 1.0f))
            }

        }
    }

}