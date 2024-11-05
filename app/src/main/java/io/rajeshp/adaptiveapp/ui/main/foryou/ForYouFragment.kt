package io.rajeshp.adaptiveapp.ui.main.foryou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.rajeshp.adaptiveapp.databinding.FragmentForyouBinding
import io.rajeshp.adaptiveapp.ui.common.MenuAdapter

class ForYouFragment : Fragment() {
    private var _binding: FragmentForyouBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ForYouViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForyouBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val menuAdapter = MenuAdapter {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        with(binding.menu) {
            isNestedScrollingEnabled = false
            adapter = menuAdapter
        }

        viewModel.menuItems.observe(viewLifecycleOwner) {
            menuAdapter.menuItems = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}