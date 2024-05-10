package com.example.auratestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.auratestapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var adapter: BootsAdapter? = BootsAdapter()
    private lateinit var binding: ActivityMainBinding
    private val bootsRepository by lazy { BootsRepository.getInstance(this.application as AuraApp) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.list) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        //TODO move to ViewModel
        MainScope().launch(Dispatchers.IO) {
            val data = bootsRepository.getBootsCountByDay()
            withContext(Dispatchers.Main) {
                adapter?.data = data
            }
        }

        binding.testButton.setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                bootsRepository.addBootEvent(System.currentTimeMillis())
            }
        }

        /*TODO Should have the ability to change the “Total dismissals allowed” and “Interval between dismissals” using UI components (can be simple edit texts).*/
    }

    override fun onDestroy() {
        adapter = null
        binding?.list?.adapter = null
        super.onDestroy()
    }
}