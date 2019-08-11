package com.raul.androidapps.cvapp.ui.education

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.repository.Repository
import com.raul.androidapps.cvapp.ui.common.BaseViewModel
import javax.inject.Inject

class EducationViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel(repository) {

    fun getEducation(): LiveData<List<String>> = repository.getEducation(getGistId())

}
