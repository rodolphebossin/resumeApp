package com.rodolphebossin.resumeapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodolphebossin.resumeapp.data.DataFormation
import com.rodolphebossin.resumeapp.data.Formation
import com.rodolphebossin.resumeapp.ui.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    fun onExpandedChange(expanded: Boolean) {
        isExpanded = expanded
    }

    // Skill displayed
    var skill: Int = 0

    fun onSkillChange(position: Int) {
        skill = position
    }

    // Screen selected
    var selectedScreen: Int = 0

    fun onScreenChange(selection: Int) {
        selectedScreen = selection
    }

    // Home Screen Button clicked
    var clicked: MutableList<Boolean> = MutableList(allScreens.size) { false }

    fun onClickedChange(modifiedList: MutableList<Boolean>) {
        clicked = modifiedList
    }

    // Video playback position
    var playbackPosition: Long = 0L

    /* fun onPositionChanged(position: Long){
         playbackPosition = position
     }
 */

    // Formation
    private val _formationCards = MutableStateFlow(listOf<Formation>()) // All formation cards
    val cards: StateFlow<List<Formation>> get() = _formationCards

    private val _coloredFormationCardIdsList =
        MutableStateFlow(listOf<Int>()) // Ones that have different background color
    val coloredFormationCardIdsList: StateFlow<List<Int>> get() = _coloredFormationCardIdsList

    private val _revealedFormationCardIdsList =
        MutableStateFlow(listOf<Int>()) // Ones that are displayed
    val revealedFormationCardIdsList: StateFlow<List<Int>> get() = _revealedFormationCardIdsList

    init {
        getData()
    }

    // Generate the mutable list from the hard coded data
    private fun getData() {
        viewModelScope.launch(Dispatchers.Default) {
            val formationsList = DataFormation.formations
            _formationCards.emit(formationsList)
        }
    }

    // when the item is swiped right and icon exposed, the card is added to the list
    fun onItemExpanded(cardId: Int) {
        if (_revealedFormationCardIdsList.value.contains(cardId)) return
        _revealedFormationCardIdsList.value =
            _revealedFormationCardIdsList.value.toMutableList().also { list ->
                list.add(cardId)
            }
    }

    // when the item is swiped left and icon hidden, it is removed from the list
    fun onItemCollapsed(cardId: Int) {
        if (!_revealedFormationCardIdsList.value.contains(cardId)) return
        _revealedFormationCardIdsList.value =
            _revealedFormationCardIdsList.value.toMutableList().also { list ->
                list.remove(cardId)
            }
    }

    // move item to bottom of list when click on icon
    fun onClickedChange(cardId: Int) {
        if (!coloredFormationCardIdsList.value.contains(cardId)) {
            _coloredFormationCardIdsList.value =
                _coloredFormationCardIdsList.value.toMutableList().also { list ->
                    list.add(cardId)
                }
        } else _coloredFormationCardIdsList.value =
            _coloredFormationCardIdsList.value.toMutableList().also { list ->
                list.remove(cardId)
            }

        if (!_revealedFormationCardIdsList.value.contains(cardId)) return
        _revealedFormationCardIdsList.value =
            _revealedFormationCardIdsList.value.toMutableList().also { list ->
                list.remove(cardId)
            }

    }

}
