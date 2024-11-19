package io.rajeshp.adaptiveapp.ui.main.dashboard

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
import io.rajeshp.adaptiveapp.databinding.FragmentDashboardBinding
import io.rajeshp.adaptiveapp.ui.common.BaseTwoPaneFragment
import io.rajeshp.adaptiveapp.ui.common.GridMenuAdapter
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter

class DashboardFragment : BaseTwoPaneFragment() {

    private val viewModel by viewModels<DashboardViewModel>()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getSlidingPaneLayout(): SlidingPaneLayout {
        return binding.slidingPaneLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailNavController = childFragmentManager.findFragmentById(R.id.detail_pane)!!.findNavController()

        val overview = view.findViewById<CardView>(R.id.overview)
        val quickAccess = view.findViewById<RecyclerView>(R.id.quickAccess)
        val menu = view.findViewById<RecyclerView>(R.id.menu)
        val quickAccessAdapter = GridMenuAdapter { navigateToDetails(it, detailNavController) }
        val menuAdapter = MenuAdapter { navigateToDetails(it, detailNavController) }

        overview.setOnClickListener {
            navigateToDetails("Overview", detailNavController)
        }

        with(quickAccess) {
            isNestedScrollingEnabled = false
            adapter = quickAccessAdapter
        }

        with(menu) {
            isNestedScrollingEnabled = false
            adapter = menuAdapter
        }

        with(viewModel) {
            quickAccessItems.observe(viewLifecycleOwner) {
                quickAccessAdapter.menuItems = it
            }

            menuItems.observe(viewLifecycleOwner) {
                menuAdapter.menuItems = it
            }
        }
    }

    private fun navigateToDetails(title: String, navController: NavController) {
        navController.navigate(DashboardFragmentDirections.actionDashboardToDetails(title))
        openSlidingPane()
    }
}