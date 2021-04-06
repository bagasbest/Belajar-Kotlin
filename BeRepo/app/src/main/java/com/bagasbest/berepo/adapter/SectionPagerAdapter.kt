package com.bagasbest.berepo.adapter


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bagasbest.berepo.fragment.FollowerFragment
import com.bagasbest.berepo.fragment.FollowingFragment


class SectionPagerAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {

    var name: String? = null

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {

            0 -> fragment = FollowingFragment.newInstance(name)
            1 -> fragment = FollowerFragment.newInstance(name)
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

}