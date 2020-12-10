package com.pavellukyanov.myaaproject.adapters

import android.content.Context
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

class ActorsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val actorPhoto: ImageView = itemView.findViewById(R.id.actorPhoto)
    private val actorName: TextView = itemView.findViewById(R.id.actorName)

    fun bind(actor: Actor) {
        Glide.with(itemView.context)
            .load(actor.actorPicture)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(actorPhoto)

        actorName.text = actor.actorName
    }

}