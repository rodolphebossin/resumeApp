package com.rodolphebossin.resumeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Rodolphe Bossin on 04/02/2022.
 */
class ResumeViewModel : ViewModel() {

    var screenChipScrollableTabRowScrollPosition: Int = 0

    fun onChangeScreenChipScrollableTabRowScrollPosition(position: Int){
        screenChipScrollableTabRowScrollPosition = position
    }

}
