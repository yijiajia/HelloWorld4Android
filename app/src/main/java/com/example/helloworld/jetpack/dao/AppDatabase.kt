package com.example.helloworld.jetpack.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.helloworld.jetpack.model.Book
import com.example.helloworld.jetpack.model.User

@Database(version = 2, entities = [User::class,Book::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao

    abstract fun getBookDao() : BookDao

    companion object {

        // 版本1->版本2
        val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id Integer primary key autoincrement not null, name text not null, pages integer not null)")
            }
        }

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context) : AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,"app_database")
                .addMigrations(MIGRATION_1_2)
                .build().apply {
                    instance = this
                }
        }
    }

}