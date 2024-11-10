package io.rajeshp.adaptiveapp.ui.main.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.rajeshp.adaptiveapp.R
import io.rajeshp.adaptiveapp.ui.common.GridMenuAdapter
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter
import io.rajeshp.adaptiveapp.ui.main.more.MoreFragmentDirections

class PaymentsFragment : Fragment() {

    private val viewModel by viewModels<PaymentsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_payments, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        var navController = findNavController()
        if (isTablet) {
            val childNavHostFragment = childFragmentManager.findFragmentById(R.id.payments_nav_container) as NavHostFragment
            navController = childNavHostFragment.navController
        }

        val quickAccess = view.findViewById<RecyclerView>(R.id.quickAccess)
        val menu = view.findViewById<RecyclerView>(R.id.menu)
        val quickAccessAdapter = GridMenuAdapter { navigateToDetails(it, navController) }
        val menuAdapter = MenuAdapter { navigateToDetails(it, navController) }

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
        navController.navigate(PaymentsFragmentDirections.actionPaymentsToDetails(title))
    }
}