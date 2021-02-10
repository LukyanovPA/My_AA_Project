package com.pavellukyanov.myaaproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.models.old.Actor
import com.pavellukyanov.myaaproject.databinding.ViewHolderActorBinding

class ActorsAdapter: RecyclerView.Adapter<ActorsViewHolder>() {

    var actors: List<Actor> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        return ActorsViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = actors.size

    private fun getItem(position: Int): Actor = actors[position]
}

class ActorsViewHolder(val containerView: View): RecyclerView.ViewHolder(containerView) {
    private val binding = ViewHolderActorBinding.bind(containerView)

    fun bind(actor: Actor) {
        Glide.with(containerView.context)
            .load(actor.picture)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.actorPhoto)

        binding.actorName.text = actor.name
    }

}