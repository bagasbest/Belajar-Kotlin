package com.bagasbest.berepo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.berepo.R
import com.bagasbest.berepo.adapter.FollowingAdapter
import com.bagasbest.berepo.model.UserModel
import com.bagasbest.berepo.viewModel.FollowerViewModel
import com.bagasbest.berepo.viewModel.FollowingViewModel
import com.bagasbest.berepo.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_following.*


class FollowingFragment : Fragment() {

    companion object {
        fun newInstance(name: String?): Fragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_NAME, name)
            fragment.arguments = bundle
            return fragment
        }

        const val ARG_NAME = "name"
    }

    private lateinit var adapter: FollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowingAdapter()
        adapter.notifyDataSetChanged()

        rvFollowing.layoutManager = LinearLayoutManager(context)
        rvFollowing.adapter = adapter

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java
        )

        val dataUser = arguments?.getString(ARG_NAME)

        Log.d("DATA FRAGMENN : ", dataUser.toString())

        followingViewModel.setFollowingUser(dataUser.toString())
        followingViewModel.getFollowingUser().observe(viewLifecycleOwner, { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
            }
        })

    }
}