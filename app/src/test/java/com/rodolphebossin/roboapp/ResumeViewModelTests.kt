package com.rodolphebossin.roboapp
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Created by Rodolphe Bossin on 19/02/2022.
 */
class ResumeViewModelTests {

    @Test
    fun whenExpandMission_updatesIsExpanded() {
        val viewModel = ResumeViewModel() // Value false by default
        viewModel.onExpandedChange(true)
        assertThat(viewModel.isExpanded).isEqualTo(true)
    }
}