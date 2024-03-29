package com.bagasbest.jaramba.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.FragmentProfileBinding
import com.bagasbest.jaramba.view.activity.LoginActivity
import com.bagasbest.jaramba.view.activity.SettingActivity
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding!!.root
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