package com.pavellukyanov.myaaproject.dataMy

import com.pavellukyanov.myaaproject.R

class DataSource {

    fun generateMovies(): List<Movie> = listOf(
            Movie(
                R.drawable.movie,
                "13+",
                "Action, Adventure, Fantasy",
                4.0F, "125 Reviews",
                "Avengers: End Game",
                "137 min",
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.rbd),
                    Actor("Chris Evans", R.drawable.chrisevans),
                    Actor("Mark Ruffalo", R.drawable.markruffalo),
                    Actor("Chris Hemsworth", R.drawable.chrishem)
                )
            ),
            Movie(
                R.drawable.black_widow,
                "13+",
                "Action, Adventure, Fantasy",
                4.0F,
                "125 Reviews",
                "Black Widow",
                "137 min",
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.rbd),
                    Actor("Chris Evans", R.drawable.chrisevans),
                    Actor("Mark Ruffalo", R.drawable.markruffalo),
                    Actor("Chris Hemsworth", R.drawable.chrishem)
                )
            ),
            Movie(
                R.drawable.tenet,
                "13+",
                "Action, Adventure, Fantasy",
                3.0F,
                "125 Reviews",
                "Tenet",
                "137 min",
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.rbd),
                    Actor("Chris Evans", R.drawable.chrisevans),
                    Actor("Mark Ruffalo", R.drawable.markruffalo),
                    Actor("Chris Hemsworth", R.drawable.chrishem)
                )
            ),
            Movie(
                R.drawable.wonder_woman,
                "13+",
                "Action, Adventure, Fantasy",
                5.0F,
                "125 Reviews",
                "Wonder Woman 1984",
                "137 min",
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.rbd),
                    Actor("Chris Evans", R.drawable.chrisevans),
                    Actor("Mark Ruffalo", R.drawable.markruffalo),
                    Actor("Chris Hemsworth", R.drawable.chrishem)
                )
            ),
        ).shuffled()
    }