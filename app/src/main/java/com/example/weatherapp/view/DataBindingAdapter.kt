package com.example.weatherapp.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weatherapp.R

class DataBindingAdapter {
    companion object {

        @JvmStatic
        @BindingAdapter("setImageViaInternetUrl")
        fun setImage(imageView: ImageView, icon: String) {
            Glide.with(imageView.context)
                .load("https://openweathermap.org/img/wn/$icon@2x.png")
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}