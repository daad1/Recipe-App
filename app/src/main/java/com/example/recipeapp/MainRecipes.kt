package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRecipes: AppCompatActivity() {
    lateinit var edTitle: EditText
    lateinit var edAuthor: EditText
    lateinit var edIngredients: EditText
    lateinit var edInstruction: EditText
    lateinit var btnSave: Button
    lateinit var btnView: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_food)
        edTitle = findViewById(R.id.etTitle)
        edAuthor = findViewById(R.id.etAuthor)
        edIngredients = findViewById(R.id.etIngredients)
        edInstruction = findViewById(R.id.etInstructions)
        btnSave = findViewById(R.id.btn_Save)


        //Set Up Retrofit json call : @post
        btnSave.setOnClickListener {
            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
            apiInterface?.addRecipes(
                RecipeItem(
                    edAuthor.text.toString(),
                    edIngredients.text.toString(),
                    edInstruction.text.toString(),
                    edTitle.text.toString(),
                    0
                )
            )?.enqueue(object : Callback<RecipeItem> {
                override fun onResponse(
                    call: Call<RecipeItem>,
                    response: Response<RecipeItem>
                ) {

                    Toast.makeText(
                        applicationContext,
                        "recipe added to api",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(MainRecipes(), MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<RecipeItem>, t: Throwable) {
                    Log.d("post-error2", "$t")
                }
            })
        }
    }
}
