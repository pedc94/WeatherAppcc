package com.example.weatherapp.util

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.hideActionBar() = this.supportActionBar?.hide()

fun AppCompatActivity.showActionBar() = this.supportActionBar?.show()