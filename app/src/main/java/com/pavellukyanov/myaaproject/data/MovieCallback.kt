package com.pavellukyanov.myaaproject.data

import androidx.recyclerview.widget.DiffUtil

class MovieCallback(
    private val oldMovieList: List<Movie>,
    private val newMovieList: List<Movie>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldMovieList.size

    override fun getNewListSize(): Int = newMovieList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMovieList[oldItemPosition].name == newMovieList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMovieList[oldItemPosition] == newMovieList[newItemPosition]
}