package io.rajeshp.adaptiveapp.ui.main.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.rajeshp.adaptiveapp.databinding.FragmentPaymentsBinding
import io.rajeshp.adaptiveapp.ui.common.GridMenuAdapter
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter

class PaymentsFragment : Fragment() {
    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PaymentsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val quickAccessAdapter = GridMenuAdapter {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        val menuAdapter = MenuAdapter {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        with(binding.quickAccess) {
            isNestedScrollingEnabled = false
            adapter = quickAccessAdapter
        }

        with(binding.menu) {
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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}