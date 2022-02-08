package com.rodolphebossin.resumeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Rodolphe Bossin on 04/02/2022.
 */
class ResumeViewModel : ViewModel() {

    // Selected chip in scrollable tabRow
    var screenChipScrollableTabRowScrollPosition: Int = 0

    fun onChangeScreenChipScrollableTabRowScrollPosition(position: Int){
        screenChipScrollableTabRowScrollPosition = position
    }

    // Is this card expanded
    var isExpanded: Boolean = false

    fun onExpandedChange(expanded: Boolean){
        isExpanded = expanded
    }

    // Skill displayed
    var skill: Int = 0

    fun onSkillChange(position: Int){
        skill = position
    }

}
