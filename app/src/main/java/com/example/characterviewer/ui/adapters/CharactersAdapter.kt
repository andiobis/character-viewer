package com.example.characterviewer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.characterViewer.core.model.CharacterModel
import com.example.characterviewer.databinding.ListItemBinding

class CharactersAdapter(private val onItemClicked: (CharacterModel) -> Unit) :
    ListAdapter<CharacterModel, CharactersAdapter.CharactersViewHolder>(DiffCallback) {

    private lateinit var context: Context

    class CharactersViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(searchResult: CharacterModel, context: Context) {
            binding.title.text = searchResult.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewHolder {
        context = parent.context
        return CharactersViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, context)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return (oldItem.title == newItem.title ||
                        oldItem.subTitle == newItem.subTitle ||
                        oldItem.imageUrl == newItem.imageUrl
                        )
            }

            override fun areContentsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
