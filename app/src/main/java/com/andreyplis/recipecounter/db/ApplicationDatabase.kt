package com.andreyplis.recipecounter.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.andreyplis.recipecounter.db.dao.ProductsDao
import com.andreyplis.recipecounter.db.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductsDao


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
                instance.getProductDao().insert(ProductEntity(1, "lol", "kg", 1,1.0f))
                instance.getProductDao()
                    .insert(ProductEntity(2, "lol2", "kg", 1,1.0f))
                instance.getProductDao()
                    .insert(ProductEntity(3, "lol3", "kg", 1,1.0f))
            }

        }
    }

}