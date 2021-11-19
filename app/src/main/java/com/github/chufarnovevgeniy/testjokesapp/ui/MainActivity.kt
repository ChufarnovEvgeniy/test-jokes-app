package com.github.chufarnovevgeniy.testjokesapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.chufarnovevgeniy.testjokesapp.R
import com.github.chufarnovevgeniy.testjokesapp.databinding.ActivityMainBinding
import com.github.chufarnovevgeniy.testjokesapp.ui.api_info.ApiInfoFragment
import com.github.chufarnovevgeniy.testjokesapp.ui.jokes.JokesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            navigate(it)
        }

        if (savedInstanceState == null) {
            navigateToFragment(JokesFragment())
        }
    }

    private fun navigate(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_jokes -> {
                navigateToFragment(JokesFragment())
            }
            R.id.item_api_info -> {
                navigateToFragment(ApiInfoFragment())
            }
        }

        return true
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.containerLayout.id, fragment)
            .commit()
    }
}