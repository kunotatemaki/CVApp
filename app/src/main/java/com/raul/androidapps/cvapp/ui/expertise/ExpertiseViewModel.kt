package com.raul.androidapps.cvapp.ui.expertise

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.persistence.relations.CompanyWithAllInfo
import com.raul.androidapps.cvapp.repository.Repository
import com.raul.androidapps.cvapp.ui.common.BaseViewModel
import javax.inject.Inject

class ExpertiseViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel(repository) {

    fun getExpertise(): LiveData<List<CompanyWithAllInfo>> =
        repository.getExpertise(getGistId())

}
