package com.github.chufarnovevgeniy.testjokesapp.ui.jokes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.chufarnovevgeniy.testjokesapp.R
import com.github.chufarnovevgeniy.testjokesapp.databinding.ItemJokeBinding
import com.github.chufarnovevgeniy.testjokesapp.domain.entities.JokeEntity

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokeViewHolder>() {
    private var data: List<JokeEntity> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<JokeEntity>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(parent)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class JokeViewHolder(
        viewGroup: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_joke, viewGroup, false)
    ) {
        private val binding = ItemJokeBinding.bind(itemView)

        fun bind(jokeEntity: JokeEntity) {
            binding.jokeTextView.text = jokeEntity.joke
        }
    }
}