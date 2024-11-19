package io.rajeshp.adaptiveapp.ui.common

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.slidingpanelayout.widget.SlidingPaneLayout

abstract class BaseTwoPaneFragment() : Fragment() {

    protected lateinit var backPressHandler: SlidingPaneBackPressHandler

    abstract fun getSlidingPaneLayout(): SlidingPaneLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slidingPaneLayout = getSlidingPaneLayout()
        slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED

        backPressHandler = SlidingPaneBackPressHandler(slidingPaneLayout)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressHandler)
    }

    protected fun openSlidingPane() {
        getSlidingPaneLayout().openPane()
    }
}

class SlidingPaneBackPressHandler(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(false), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
        slidingPaneLayout.doOnLayout {
            syncState()
        }
    }

    private fun syncState() {
        isEnabled = slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
    }

    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelOpened(panel: View) {
        syncState()
    }

    override fun onPanelClosed(panel: View) {
        syncState()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) { }
}