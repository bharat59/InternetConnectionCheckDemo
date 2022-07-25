package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    //init network helper
    private var networkConnection : LiveData<Boolean> = MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //implementation of network helper which we created
        networkConnection = NetworkConnectionHelper(applicationContext)

        //ser observer for networkConnection
        networkConnection.observe(this) { isConnected ->
            if (isConnected) {
                Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
                binding.networkError.root.visibility= View.GONE
            } else {
                Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show()
                binding.networkError.root.visibility= View.VISIBLE
            }
        }
    }
}