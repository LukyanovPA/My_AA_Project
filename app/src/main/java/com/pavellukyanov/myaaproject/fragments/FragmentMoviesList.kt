package com.pavellukyanov.myaaproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pavellukyanov.myaaproject.R

class FragmentMoviesList: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.cardViewMovie)
            .setOnClickListener{
                showMoviesDetailsFragment()
            }
    }

    private fun showMoviesDetailsFragment() {
        val supportFragmentManager = activity?.supportFragmentManager
        supportFragmentManager?.beginTransaction()
            ?.replace(R.id.flFragmetn, FragmentMoviesDetails())
            ?.addToBackStack("FragmentMovieDetails")
            ?.commit()
    }
}