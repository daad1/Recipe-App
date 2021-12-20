package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("recipes/")
    fun getRecipes(): Call<ArrayList<RecipeItem>>

    @POST("recipes/")
    fun addRecipes(
        @Body data: RecipeItem
    ): Call<RecipeItem>
}