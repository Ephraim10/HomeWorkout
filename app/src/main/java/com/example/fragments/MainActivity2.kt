package com.example.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Call replaceFragment() on initialization
        replaceFragment(Training())

        binding.bottomNavigationView2.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.discover -> replaceFragment(Discover())
                R.id.report -> replaceFragment(Report())
                R.id.settings -> replaceFragment(Settings())
                R.id.home -> replaceFragment(Training())
                else -> return@setOnItemSelectedListener false
            }

            return@setOnItemSelectedListener true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
