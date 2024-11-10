package io.rajeshp.adaptiveapp.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.rajeshp.adaptiveapp.R
import io.rajeshp.adaptiveapp.ui.common.GridMenuAdapter
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter

class DashboardFragment : Fragment() {

    private val viewModel by viewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        var navController = findNavController()
        if (isTablet) {
            val childNavHostFragment = childFragmentManager.findFragmentById(R.id.dashboard_nav_container) as NavHostFragment
            navController = childNavHostFragment.navController
        }

        val overview = view.findViewById<CardView>(R.id.overview)
        val quickAccess = view.findViewById<RecyclerView>(R.id.quickAccess)
        val menu = view.findViewById<RecyclerView>(R.id.menu)
        val quickAccessAdapter = GridMenuAdapter { navigateToDetails(it, navController) }
        val menuAdapter = MenuAdapter { navigateToDetails(it, navController) }

        overview.setOnClickListener {
            navigateToDetails("Overview", navController)
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
    }
}