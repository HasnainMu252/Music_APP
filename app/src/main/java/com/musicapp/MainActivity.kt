package com.musicapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var itsRecyler : RecyclerView

    lateinit var itsAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        itsRecyler = findViewById(R.id.RecyclerView)





        val Retrofitudler = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")

            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val RetrofitData = Retrofitudler.getData("eminem")

        RetrofitData.enqueue(object : Callback<MusicData?> {
            override fun onResponse(call: Call<MusicData?>, response: Response<MusicData?>) {
                val dataList = response.body()?.data!!
//                val text = findViewById<TextView>(R.id.ViewText)
//                text.text = dataList.toString()

                itsAdapter = MyAdapter(context = this@MainActivity,dataList)

                itsRecyler.adapter = itsAdapter
                itsRecyler.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("DataList", "Size: ${dataList.size}, Contents: $dataList")

            }

            override fun onFailure(p0: Call<MusicData?>, p1: Throwable) {
                Log.d("Failure", "onResponse: "+p1.message)


            }
        })

    }
}