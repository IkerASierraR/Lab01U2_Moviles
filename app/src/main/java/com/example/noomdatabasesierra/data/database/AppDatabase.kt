package com.example.noomdatabasesierra.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noomdatabasesierra.data.dao.UserDao
import com.example.noomdatabasesierra.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}