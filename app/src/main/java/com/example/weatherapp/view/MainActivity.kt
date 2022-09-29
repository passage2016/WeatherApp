package com.example.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var binding : ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    lateinit var adapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val assetManager = this.applicationContext.assets
        val apikey = String(assetManager.open("apikey.txt").readBytes())

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.ibSearchLocation.setOnClickListener {
            binding.clLocation.visibility = View.GONE
            binding.clSearch.visibility = View.VISIBLE
        }

        binding.btnSearch.setOnClickListener {
            binding.clLocation.visibility = View.VISIBLE
            binding.clSearch.visibility = View.GONE
            if (!binding.etSearch.text.isNullOrEmpty()){
                Log.e("City", binding.etSearch.text.toString())
                binding.tvCity.text = binding.etSearch.text.toString()
                viewModel.getWeather(binding.etSearch.text.toString(), apikey)
                viewModel.getForecast(binding.etSearch.text.toString(), apikey)
            }
        }

        viewModel.weatherLiveData.observe(this){
            binding.tvTemp.text = it.main.temp.toString() + "℃"
        }
        viewModel.forecastLiveData.observe(this){
            adapter = ForecastAdapter(this, it.list)
            binding.rrForecast.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.rrForecast.adapter = adapter
        }

    }

}