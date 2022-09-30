package com.example.weatherapp.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var locationManager: LocationManager
    lateinit var binding: ActivityMainBinding
    lateinit var apikey: String
    var lat: Double? = null
    var lon: Double? = null
    val viewModel: MainViewModel by viewModels()
    lateinit var adapter: ForecastAdapter
    lateinit var airPollutionAdapter: AirPollutionAdapter
    private val locationPermissionCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val assetManager = this.applicationContext.assets
        apikey = String(assetManager.open("apikey.txt").readBytes())
        getCurrentLocation()

        binding.ibSearchLocation.setOnClickListener {
            binding.clLocation.visibility = View.GONE
            binding.clSearch.visibility = View.VISIBLE
        }

        binding.ibCurrectLocation.setOnClickListener {
            if(lat == null || lon == null){
                getCurrentLocation()
            } else {
                viewModel.getWeather(lat!!, lon!!, apikey)
            }

        }

        binding.btnSearch.setOnClickListener {
            binding.clLocation.visibility = View.VISIBLE
            binding.clSearch.visibility = View.GONE
            if (!binding.etSearch.text.isNullOrEmpty()) {
                viewModel.getWeather(binding.etSearch.text.toString(), apikey)
            }
        }

        viewModel.weatherLiveData.observe(this) {
            viewModel.getForecast(it.coord.lat, it.coord.lon, apikey)
            viewModel.getAirPollution(it.coord.lat, it.coord.lon, apikey)
            binding.tvCity.text = it.name
            binding.tvTemp.text = it.main.temp.toString() + "℃"
        }
        viewModel.forecastLiveData.observe(this) {
            adapter = ForecastAdapter(this, it.list)
            binding.rrForecast.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.rrForecast.adapter = adapter
        }

        viewModel.airPollutionLiveData.observe(this){
            airPollutionAdapter = AirPollutionAdapter(this, it.list)
            binding.rrAirPollution.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.rrAirPollution.adapter = airPollutionAdapter
        }

    }

    fun getCurrentLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            5000,
            5f,
            this
        )
    }

    override fun onLocationChanged(location: Location) {
        lat = location.latitude
        lon = location.longitude
        viewModel.getWeather(lat!!, lon!!, apikey)
    }

}