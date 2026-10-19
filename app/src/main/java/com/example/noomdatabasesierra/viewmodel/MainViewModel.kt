package com.example.noomdatabasesierra.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noomdatabasesierra.application.RoomApplication
import com.example.noomdatabasesierra.data.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    val users = RoomApplication.appDatabase.userDao().getAll()

    fun guardar(nombre: String, apellido: String): String ?{

        if(nombre.isBlank()) return "El nombre es obligatorio"
        if(apellido.isBlank()) return "El apellido es obligatorio"

        val user = UserEntity(nombre = nombre.trim(), apellido = apellido.trim())
        viewModelScope.launch(Dispatchers.IO) {
            RoomApplication.appDatabase.userDao().insertUser(user)
            Log.d("ROOM_DB", "Registro insertado: ${user.nombre}")
        }
        return null
    }

    fun eliminar (user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            RoomApplication.appDatabase.userDao().deleteUser(user)
            Log.d("ROOM_DB", "Registro eliminado: ${user.nombre}")
        }

    }

}
