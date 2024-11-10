package io.rajeshp.adaptiveapp.ui.main.foryou

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
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter
import io.rajeshp.adaptiveapp.ui.main.more.MoreFragmentDirections

class ForYouFragment : Fragment() {

    private val viewModel by viewModels<ForYouViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_foryou, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        var navController = findNavController()
        if (isTablet) {
            val childNavHostFragment = childFragmentManager.findFragmentById(R.id.foryou_nav_container) as NavHostFragment
            navController = childNavHostFragment.navController
        }

        val insights = view.findViewById<CardView>(R.id.insights)
        val events = view.findViewById<CardView>(R.id.events)
        val menu = view.findViewById<RecyclerView>(R.id.menu)
        val menuAdapter = MenuAdapter { navigateToDetails(it, navController) }

        insights.setOnClickListener {
            navigateToDetails("Insights", navController)
        }

        events.setOnClickListener {
            navigateToDetails("Events", navController)
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
    }
}