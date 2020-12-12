package com.pavellukyanov.myaaproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.Actor
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_actor.view.*

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

class ActorsViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(actor: Actor) {
        Glide.with(containerView.context)
            .load(actor.picture)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(containerView.actorPhoto)

        containerView.actorName.text = actor.name
    }

}