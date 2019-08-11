package com.raul.androidapps.cvapp.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.MainActivityBinding
import com.raul.androidapps.cvapp.ui.common.CVAppViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: CVAppViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        val navController = findNavController(this, R.id.fragment_container)
        binding.bottomNavigation.setupWithNavController(navController)

    }

}
