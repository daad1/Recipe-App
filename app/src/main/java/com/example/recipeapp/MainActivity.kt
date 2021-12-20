package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MainActivity : AppCompatActivity() {

    var recipesList = ArrayList<RecipeCard>()
    lateinit var rvMain: RecyclerView
    lateinit var adapter: RecyclerViewAdapter
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up The RecyclerView
        rvMain = findViewById(R.id.rvMain)
        adapter = RecyclerViewAdapter(recipesList)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)


        //Set Up The Retrofit Json Call : @GET

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<ArrayList<RecipeItem>>? = apiInterface?.getRecipes()

        call?.enqueue(object : Callback<ArrayList<RecipeItem>> {
            override fun onResponse(
                call: Call<ArrayList<RecipeItem>>,
                response: Response<ArrayList<RecipeItem>>
            ) {
                try {
                    val response = response.body()!!
                    for (i in 0 until response!!.size) {
                        val title = response[i].title
                        val author = response[i].author
                        val ingredients = response[i].ingredients
                        val instructions = response[i].instructions

                        val recipeObject = RecipeCard(title, author, ingredients, instructions)
                        recipesList.add(recipeObject)
                        adapter.notifyDataSetChanged()


                    }
                } catch (e: Exception) {
                    Log.d("Error", "$e")

                }
            }

            override fun onFailure(call: Call<ArrayList<RecipeItem>>, t: Throwable) {
                Log.d("Error", "$t")
            }
        })

        // UI interactions

        button = findViewById(R.id.btAdd)

        button.setOnClickListener {
            intent = Intent(applicationContext, MainRecipes::class.java)
            startActivity(intent)

        }


    }
}