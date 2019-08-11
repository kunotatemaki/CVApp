package com.raul.androidapps.cvapp.ui.skill

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.repository.Repository
import com.raul.androidapps.cvapp.ui.common.BaseViewModel
import javax.inject.Inject

class SkillViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel(repository) {

    fun getSkills(): LiveData<List<String>> = repository.getSkills(getGistId())
}
