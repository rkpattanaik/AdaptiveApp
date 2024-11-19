package io.rajeshp.adaptiveapp.ui.main.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import io.rajeshp.adaptiveapp.R
import io.rajeshp.adaptiveapp.databinding.FragmentForyouBinding
import io.rajeshp.adaptiveapp.databinding.FragmentMoreBinding
import io.rajeshp.adaptiveapp.ui.common.BaseTwoPaneFragment
import io.rajeshp.adaptiveapp.ui.common.GridMenuAdapter
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter

class MoreFragment : BaseTwoPaneFragment() {

    private val viewModel by viewModels<MoreViewModel>()

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getSlidingPaneLayout(): SlidingPaneLayout {
        return binding.slidingPaneLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailNavController = childFragmentManager.findFragmentById(R.id.detail_pane)!!.findNavController()

        val quickAccess = view.findViewById<RecyclerView>(R.id.quickAccess)
        val menu = view.findViewById<RecyclerView>(R.id.menu)
        val quickAccessAdapter = GridMenuAdapter { navigateToDetails(it, detailNavController) }
        val menuAdapter = MenuAdapter { navigateToDetails(it, detailNavController) }

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
        navController.navigate(MoreFragmentDirections.actionMoreToDetails(title))
        openSlidingPane()
    }
}