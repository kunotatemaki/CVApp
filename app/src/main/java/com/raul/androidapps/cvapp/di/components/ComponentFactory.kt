package com.raul.androidapps.cvapp.di.components

import com.raul.androidapps.cvapp.CVAppApplication


object ComponentFactory {

    fun component(context: CVAppApplication): CVAppComponent {
        return DaggerCVAppComponent.builder()
                .application(context)
                .build()
    }

}