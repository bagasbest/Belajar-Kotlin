package com.bagasbest.berepo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.berepo.R
import com.bagasbest.berepo.adapter.FollowerAdapter
import com.bagasbest.berepo.model.UserModel
import com.bagasbest.berepo.viewModel.FollowerViewModel
import kotlinx.android.synthetic.main.fragment_follower.*


class FollowerFragment : Fragment() {

    companion object {
        fun newInstance(name: String?): Fragment {
            val fragment = FollowerFragment()
            val bundle = Bundle()
            bundle.putString(ARG_NAME, name)
            fragment.arguments = bundle
            return fragment
        }
        const val ARG_NAME = "name"
    }

    private lateinit var adapter: FollowerAdapter
    private lateinit var followerViewModel: FollowerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowerAdapter()
        adapter.notifyDataSetChanged()

        rvFollowers.layoutManager = LinearLayoutManager(context)
        rvFollowers.adapter = adapter


        followerViewModel =
            ViewModelProvider(this, NewInstanceFactory()).get(FollowerViewModel::class.java)

        val dataUser = arguments?.getString(ARG_NAME)
        Log.d("DATA FOLLOWER NAME: ", dataUser.toString())

        followerViewModel.setFollowerUser(dataUser.toString())
        followerViewModel.getFollowerUser().observe(viewLifecycleOwner, { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
            }
        })

    }


}