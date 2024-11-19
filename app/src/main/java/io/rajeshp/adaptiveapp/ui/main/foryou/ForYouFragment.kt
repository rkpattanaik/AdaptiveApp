package io.rajeshp.adaptiveapp.ui.main.foryou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import io.rajeshp.adaptiveapp.R
import io.rajeshp.adaptiveapp.databinding.FragmentForyouBinding
import io.rajeshp.adaptiveapp.ui.common.BaseTwoPaneFragment
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter

class ForYouFragment : BaseTwoPaneFragment() {

    private val viewModel by viewModels<ForYouViewModel>()

    private var _binding: FragmentForyouBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForyouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getSlidingPaneLayout(): SlidingPaneLayout {
        return binding.slidingPaneLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailNavController = childFragmentManager.findFragmentById(R.id.detail_pane)!!.findNavController()

        val insights = view.findViewById<CardView>(R.id.insights)
        val events = view.findViewById<CardView>(R.id.events)
        val menu = view.findViewById<RecyclerView>(R.id.menu)
        val menuAdapter = MenuAdapter { navigateToDetails(it, detailNavController) }

        insights.setOnClickListener {
            navigateToDetails("Insights", detailNavController)
        }

        events.setOnClickListener {
            navigateToDetails("Events", detailNavController)
        }

        with(menu) {
            isNestedScrollingEnabled = false
            adapter = menuAdapter
        }

        viewModel.menuItems.observe(viewLifecycleOwner) {
            menuAdapter.menuItems = it
        }

    }

    private fun navigateToDetails(title: String, navController: NavController) {
        navController.navigate(ForYouFragmentDirections.actionForyouToDetails(title))
        openSlidingPane()
    }
}