package com.example.testtask


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.FavJobFragment
import com.example.testtask.JobFragment
import com.example.testtask.R
import com.example.testtask.viewmodel.NavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navigationViewModel: NavigationViewModel
    private lateinit var jobFragment: JobFragment
    private lateinit var favJobFragment: FavJobFragment
    private var activeFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationViewModel = ViewModelProvider(this)[NavigationViewModel::class.java]

        jobFragment = JobFragment()
        favJobFragment = FavJobFragment()

        activeFragment = jobFragment

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, favJobFragment, "FavJobFragment")
            .hide(favJobFragment)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, jobFragment, "JobFragment")
            .commit()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_search -> showFragment(jobFragment)
                R.id.navigation_favorites -> showFragment(favJobFragment)
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment) {
        if (fragment != activeFragment) {
            supportFragmentManager.beginTransaction()
                .hide(activeFragment!!)
                .show(fragment)
                .commit()
            activeFragment = fragment
        }
    }
}
