package com.adhimbagas.finalprojectskripsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
        val navigationHost = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navigationHost.navController
        bottomNavigationView.setupWithNavController(navController)



    }



}