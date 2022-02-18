package com.rodolphebossin.resumeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodolphebossin.resumeapp.ui.Screens

/**
 * Created by Rodolphe Bossin on 04/02/2022.
 */
class ResumeViewModel : ViewModel() {

/*    // Selected chip in scrollable tabRow
    var screenChipScrollableTabRowScrollPosition: Int = 0

    fun onChangeScreenChipScrollableTabRowScrollPosition(position: Int){
        screenChipScrollableTabRowScrollPosition = position
    }*/

    val allScreens = listOf(
        Screens.Home,
        Screens.Bio,
        Screens.Forces,
        Screens.Parcours,
        Screens.Competences,
        Screens.Technos,
        Screens.Formation,
        Screens.Loisirs
    )

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

    // Screen selected
    var selectedScreen: Int = 0

    fun onScreenChange(selection: Int){
        selectedScreen = selection
    }

    // Home Screen Button clicked
    var clicked: MutableList<Boolean> = MutableList(allScreens.size) {false}

    fun onClickedChange(modifiedList: MutableList<Boolean>){
        clicked = modifiedList
    }

}
