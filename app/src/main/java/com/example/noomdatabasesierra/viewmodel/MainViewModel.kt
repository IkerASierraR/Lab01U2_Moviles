package com.example.noomdatabasesierra.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noomdatabasesierra.application.RoomApplication
import com.example.noomdatabasesierra.data.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    val users = RoomApplication.appDatabase.userDao().getAll()

    fun guardar(nombre: String, apellido: String, email: String): String ?{

        if(nombre.isBlank()) return "El nombre es obligatorio"
        if(apellido.isBlank()) return "El apellido es obligatorio"
        if(email.isBlank()) return "El email es obligatorio"
        if(!nombre.trim().all { it.isLetter() || it == ' ' }) return "El nombre solo debe contener letras"
        if(!apellido.trim().all { it.isLetter() || it == ' ' }) return "El apellido solo debe contener letras"
        if(email.isNotBlank() && (!email.contains("@") || !email.contains("."))) return "El email no es válido"

        val user = UserEntity(
            nombre = nombre.trim(),
            apellido = apellido.trim(),
            email = email.trim()
        )
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

    fun buscarPorApellido(apellido: String): Flow<List<UserEntity>> {
        return RoomApplication.appDatabase.userDao().searchByLastName("%${apellido.trim()}%")
    }

}
