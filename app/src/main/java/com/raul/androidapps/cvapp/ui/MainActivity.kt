package com.raul.androidapps.cvapp.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.CVAppBindingComponent
import com.raul.androidapps.cvapp.databinding.MainActivityBinding
import com.raul.androidapps.cvapp.resources.ResourcesManagerImpl
import com.raul.androidapps.cvapp.ui.common.CVAppViewModelFactory
import com.raul.androidapps.cvapp.utils.ViewUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.math.abs

class MainActivity : DaggerAppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: CVAppViewModelFactory

    @Inject
    lateinit var resourcesManager: ResourcesManagerImpl

    @Inject
    lateinit var viewUtils: ViewUtils

    @Inject
    lateinit var cvAppBindingComponent: CVAppBindingComponent

    private var originalProfilePicHeight = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.main_activity, cvAppBindingComponent)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding.appbarLayoutMainActivity.addOnOffsetChangedListener(this)

        val navController = findNavController(this, R.id.fragment_container)
        binding.bottomNavigation.setupWithNavController(navController)
        setToolbar(binding.toolbarMainActivity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            updateStatusBar(resourcesManager.getColor(android.R.color.white))
        }

        viewModel.getProfile().observe({ lifecycle }) {
            it?.let {
                binding.profile = it
                binding.profilePicLayer.visibility = View.VISIBLE
                setTitle(it.name)
            }
        }

    }

    private fun setToolbar(toolbar: Toolbar, title: String? = null) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowTitleEnabled(true)
            this.title = title
        }
    }

    private fun setTitle(title: String) {
        binding.collapsingToolbarMainActivity.title = title
        supportActionBar?.title = title
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun updateStatusBar(color: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val setStatusBarThemeAsDark =
                if (color == resourcesManager.getColor(android.R.color.transparent)) {
                    false
                } else {
                    viewUtils.hasEnoughContrast(color)
                }
            setSystemBarTheme(setStatusBarThemeAsDark)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun setSystemBarTheme(darkBackgroundTheme: Boolean) {
        val lFlags = this.window.decorView.systemUiVisibility
        this.window.decorView.systemUiVisibility =
            if (darkBackgroundTheme) lFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() else lFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {

        if (originalProfilePicHeight < 0) {
            originalProfilePicHeight = binding.profilePic.height
        }

        val percentage = abs(verticalOffset.toFloat() / appBarLayout.totalScrollRange)

        val finalHeight = binding.toolbarMainActivity.height - viewUtils.getPxFromDp(resourcesManager, 16f)
        val diff = originalProfilePicHeight - finalHeight
        val paramsProfilePic = binding.profilePic.layoutParams
        paramsProfilePic.height = (originalProfilePicHeight - diff * percentage).toInt()
        paramsProfilePic.width = paramsProfilePic.height
        binding.profilePic.requestLayout()
        val paramsProfilePicLayer = binding.profilePicLayer.layoutParams
        val frameExtraWidth = viewUtils.getPxFromDp(resourcesManager, 10f)
        paramsProfilePicLayer.height = paramsProfilePic.height + frameExtraWidth.toInt()
        paramsProfilePicLayer.width = paramsProfilePicLayer.height
        binding.profilePicLayer.requestLayout()

        binding.backgroundPic.alpha = 0.4f - percentage
        binding.profilePicLayer.alpha = 1 - percentage

    }
}

