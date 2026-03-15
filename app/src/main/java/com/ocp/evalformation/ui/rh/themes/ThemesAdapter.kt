package com.ocp.evalformation.ui.rh.themes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocp.evalformation.data.local.entity.ThemeEntity
import com.ocp.evalformation.databinding.ItemThemeBinding

class ThemesAdapter : ListAdapter<ThemeEntity, ThemesAdapter.ThemeViewHolder>(DIFF) {

    inner class ThemeViewHolder(private val binding: ItemThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(theme: ThemeEntity) {
            binding.tvThemeNom.text = theme.nom
            binding.tvThemeObjectif.text = theme.objectifPedagogique
            binding.tvThemeId.text = "#${theme.id}"
            binding.ivSyncStatus.setImageResource(
                if (theme.syncedToFirebase)
                    android.R.drawable.presence_online
                else
                    android.R.drawable.presence_away
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val binding = ItemThemeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ThemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<ThemeEntity>() {
            override fun areItemsTheSame(a: ThemeEntity, b: ThemeEntity) = a.id == b.id
            override fun areContentsTheSame(a: ThemeEntity, b: ThemeEntity) = a == b
        }
    }
}
