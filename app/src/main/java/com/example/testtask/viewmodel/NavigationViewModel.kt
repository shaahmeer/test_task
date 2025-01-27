package com.example.testtask.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.FavJobFragment
import com.example.testtask.JobFragment
import com.example.testtask.R

class NavigationViewModel : ViewModel() {

    private val _currentFragment = MutableLiveData<Fragment>()
    val currentFragment: LiveData<Fragment> get() = _currentFragment

    init {
        _currentFragment.value = JobFragment() // Default fragment
    }

    fun navigateTo(menuItemId: Int) {
        val newFragment = when (menuItemId) {
            R.id.navigation_search -> JobFragment() // JobFragment for home
            R.id.navigation_favorites -> FavJobFragment() // FavJobFragment for favorites
            else -> null
        }
        newFragment?.let { _currentFragment.value = it }
    }
}
