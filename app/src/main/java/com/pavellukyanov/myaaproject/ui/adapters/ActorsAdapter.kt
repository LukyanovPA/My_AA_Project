package com.pavellukyanov.myaaproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pavellukyanov.myaaproject.data.models.networkmodels.Cast
import com.pavellukyanov.myaaproject.databinding.ViewHolderActorBinding

class ActorsAdapter : RecyclerView.Adapter<ActorsViewHolder>() {

    var actors: List<Cast> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val binding =
            ViewHolderActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = actors.size

    private fun getItem(position: Int): Cast = actors[position]
}

class ActorsViewHolder(private val binding: ViewHolderActorBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cast: Cast) {
        Glide.with(itemView.context)
            .load(cast.profilePoster)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.actorPhoto)

        binding.actorName.text = cast.originalName
        binding.character.text = cast.character
    }

}