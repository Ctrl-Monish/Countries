package com.internship.mycountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        fetchData()
        mAdapter = CountriesAdapter()
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        val url = "https://restcountries.eu/rest/v2/region/asia"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
                {response ->
                    val countryArray = ArrayList<Countries>()
                    for (i in 0 until response.length()) {
                        val countryJsonObject = response.getJSONObject(i)
                        val country = Countries(
                            countryJsonObject.getString("name"),
                            countryJsonObject.getString("capital"),
                            countryJsonObject.getString("region"),
                            countryJsonObject.getString("subregion"),
                            countryJsonObject.getString("population"),
                            countryJsonObject.getString("borders"),
                            countryJsonObject.getString("languages"),
                            countryJsonObject.getString("flag"),
                        )
                        countryArray.add(country)
                    }
                    mAdapter.updateCountry(countryArray)
                },
            {
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }
}