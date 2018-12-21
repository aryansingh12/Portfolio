package com.himanshurawat.portfolio.ui

interface MainActivityContract {


    interface Model{

    }

    interface View {
        fun loadPersonalFragment()

        fun loadEducationFragment()

        fun popToPersonalFragment()

        fun popFragment()

        fun updateSeekbar(progress: Int)
        fun updateStepTextView(step: String)
        fun updateStepTitleTextView(title: String)

    }

    interface Presenter{
        fun nextFragment(fragmentTag: String): String
        fun previousFragment(fragmentTag: String): String
    }
}