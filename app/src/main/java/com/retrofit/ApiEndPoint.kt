package com.retrofit

import com.retrofit.dominio.Usuario
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {
    @GET("users")
    fun obterUsuarios(): Call<List<Usuario>>
}