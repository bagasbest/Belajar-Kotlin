package com.bagasbest.fundamental2.myTabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.databinding.FragmentMyTabLayoutHomeBinding
import com.bagasbest.fundamental2.databinding.FragmentProfileBinding


class MyTabLayoutHomeFragment : Fragment() {

    private var _binding: FragmentMyTabLayoutHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyTabLayoutHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

}