package com.bagasbest.jaramba.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.FragmentProfileBinding
import com.bagasbest.jaramba.view.activity.LoginActivity
import com.bagasbest.jaramba.view.activity.SettingActivity
import com.bagasbest.jaramba.viewmodel.viewmodel.ProfileViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initViewModel()

        binding?.cityTimeIv?.let {
            Glide
                .with(this)
                .load(R.drawable.city_morning)
                .into(it)
        }


        return binding!!.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ProfileViewModel::class.java]
        val uid = FirebaseAuth.getInstance().currentUser?.uid


        if (uid != null) {
            viewModel.setProfile(uid, requireContext())
        }
        viewModel.getProfileMutableLiveData().observe(viewLifecycleOwner, { data ->
            if (data != null) {
                binding?.emailEt?.setText(data[0].email)
                binding?.usernameEt?.setText(data[0].username)
                binding?.phoneEt?.setText(data[0].phone)
                binding?.userImageIv?.let {
                    Glide
                        .with(this)
                        .load(data[0].image)
                        .into(it)
                }
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnLogout?.setOnClickListener {
            logoutFromApplication()
        }

        binding?.settingIv?.setOnClickListener {
            startActivity(Intent(activity, SettingActivity::class.java))
        }

    }

    private fun logoutFromApplication() {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle(R.string.registered)
        builder?.setMessage(R.string.messageRegistered)
        builder?.setPositiveButton("YES") { dialog, _ ->

            // sign out from firebsae
            FirebaseAuth.getInstance().signOut()

            // go to login activity
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            dialog.dismiss()
            startActivity(intent)

        }
        builder?.setNegativeButton("NO") { dialog, _ ->
            dialog.dismiss()
        }?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}