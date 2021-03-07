package com.main.mealplanner

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null;
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val retrofitInstance: Retrofit?
        get() {
            // has this object been created yet?
            if (retrofit == null) {
                // create it!
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                print("fo")
            }
            return retrofit
        }
}
