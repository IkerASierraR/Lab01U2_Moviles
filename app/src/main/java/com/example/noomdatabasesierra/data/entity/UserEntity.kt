package com.example.noomdatabasesierra.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    @ColumnInfo(name = "first_name") val nombre: String?,
    @ColumnInfo(name = "last_name") val apellido: String?,
    @ColumnInfo(name = "email") val email: String? = null
){

}
