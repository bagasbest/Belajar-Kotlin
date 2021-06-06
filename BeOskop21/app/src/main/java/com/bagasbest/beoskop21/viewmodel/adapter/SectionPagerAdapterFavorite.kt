package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.view.fragment.MoviesFavoriteFragment
import com.bagasbest.beoskop21.view.fragment.SeriesFavoriteFragment

class SectionPagerAdapterFavorite(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.favoriteMovies,
            R.string.favoriteSeries,
        )
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MoviesFavoriteFragment()
            1 -> SeriesFavoriteFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(
            TAB_TITLES[position]
        )
}