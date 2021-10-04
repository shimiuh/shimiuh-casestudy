package com.shimi.flashcardmanagement.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shimi.flashcardmanagement.databinding.FlashCardListItemBinding
import com.shimi.flashcardmanagement.model.FlashCard
import com.shimi.flashcardmanagement.modelBinding.CardsViewModel


class FlashCardAdapter(private val viewModel: CardsViewModel) : ListAdapter<FlashCard, FlashCardAdapter.FlashHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashHolder {
        return FlashHolder.from(parent,viewModel)
    }
    override fun onBindViewHolder(holder: FlashHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class FlashHolder private constructor(private val binding: FlashCardListItemBinding, private val viewModel: CardsViewModel):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FlashCard) {
            binding.viewmodel = viewModel
            binding.card = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup,viewModel: CardsViewModel): FlashHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FlashCardListItemBinding.inflate(layoutInflater, parent, false)
                return FlashHolder(binding, viewModel)
            }
        }
    }

    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
     * list that's been passed to `submitList`.
     */
    class TaskDiffCallback : DiffUtil.ItemCallback<FlashCard>() {
        override fun areItemsTheSame(oldItem: FlashCard, newItem: FlashCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FlashCard, newItem: FlashCard): Boolean {
            return oldItem == newItem
        }
    }
}



