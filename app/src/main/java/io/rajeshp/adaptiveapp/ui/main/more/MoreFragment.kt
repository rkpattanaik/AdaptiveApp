package io.rajeshp.adaptiveapp.ui.main.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.rajeshp.adaptiveapp.databinding.FragmentMoreBinding
import io.rajeshp.adaptiveapp.ui.common.GridMenuAdapter
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
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