package io.rajeshp.adaptiveapp.ui.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.rajeshp.adaptiveapp.databinding.HolderMenuItemBinding
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class MenuAdapter(
    private val onItemClick: (String) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var menuItems: List<String> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = HolderMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MenuItemViewHolder).onBind(menuItems[position])
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuItemViewHolder(
        private val binding: HolderMenuItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(title: String) {
            binding.title.text = title

            itemView.setOnClickListener {
                onItemClick(title)
            }
        }
    }
}