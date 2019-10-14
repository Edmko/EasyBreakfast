package com.example.easybreakfast.network

import com.example.easybreakfast.model.ResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.edamam.com/"
private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
interface RecipeApiService {
    @GET("search?")
    fun getProperties(  @Query("q")search:String,
                        @Query("app_id")app_id :String,
                        @Query("app_key")app_key: String)
            : Call<ResponseModel>


}
object RecipeApi {
    val retrofitService : RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}