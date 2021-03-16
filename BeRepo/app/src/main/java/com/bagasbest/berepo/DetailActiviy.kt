package com.bagasbest.berepo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.berepo.model.UserModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_activiy.*
import kotlinx.android.synthetic.main.github_user_list_item.tv_fullname
import kotlinx.android.synthetic.main.github_user_list_item.tv_username

class DetailActiviy : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activiy)


        title = "Detail Pengguna"
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        val person = intent.getParcelableExtra<UserModel>(EXTRA_USER) as UserModel
        val fullname = person.name
        val username = person.username
        val location = person.location
        val company = person.company
        val following = person.following
        val follower = person.follower
        val repository = person.repository
        val avatar = person.avatar


        tv_fullname.text = fullname
        tv_username.text = username
        tv_location.text = location
        tv_company_detail.text = company
        tv_following.text = "Following\n$following"
        tv_follower.text = "Follower\n$follower"
        tv_repos.text = "repository\n$repository"

        Glide.with(this)
            .load(avatar)
            .error(R.drawable.ic_baseline_face_24)
            .into(iv_avatar_detail)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}