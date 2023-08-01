package com.example.lifecyclelivedataviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lifecyclelivedataviewmodel.databinding.ActivityMainBinding
import com.example.lifecyclelivedataviewmodel.utils.UserResource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MyViewModel
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

//        activity hayoti
        lifecycle.addObserver(MyObserver())



        viewModel.getUserList().observe(this, Observer {
            when (it) {
                is UserResource.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is UserResource.Loading -> {
                    Log.d(TAG, "onCreate: Loading")
                }

                is UserResource.Success -> {
                    Log.d(TAG, "onCreate: ${it.list}")
                }
            }
        })


    }
}