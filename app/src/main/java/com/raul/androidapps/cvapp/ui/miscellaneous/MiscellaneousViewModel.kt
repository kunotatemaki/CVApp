package com.raul.androidapps.cvapp.ui.miscellaneous

import androidx.lifecycle.LiveData
import com.raul.androidapps.cvapp.persistence.relations.MiscellaneousWithAllInfo
import com.raul.androidapps.cvapp.repository.Repository
import com.raul.androidapps.cvapp.ui.common.BaseViewModel
import javax.inject.Inject

class MiscellaneousViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel(repository) {

    fun getMiscellaneous(): LiveData<List<MiscellaneousWithAllInfo>> =
        repository.getMiscellaneous(getGistId())

}
