package com.bagasbest.berepo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.berepo.adapter.SectionPagerAdapter
import com.bagasbest.berepo.model.FollowerModel
import com.bagasbest.berepo.model.FollowingModel
import com.bagasbest.berepo.model.UserModel
import com.bagasbest.berepo.viewModel.HomeViewModel
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_detail_activiy.*
import kotlin.math.abs


class DetailActiviy : AppCompatActivity() , AppBarLayout.OnOffsetChangedListener {

    companion object {
        const val EXTRA_USER = "extra_user"
        const val OPTION = "option"
        @StringRes
        private val tabTittles = intArrayOf(
            R.string.following,
            R.string.follower,
        )
    }

    private lateinit var detailViewModel: HomeViewModel
    private var maxScrollSize = 0
    private val PERCENTAGE_TO_ANIMATE_AVATAR = 20
    private var isAvatarShown = true
    private var isFavorite = false
    private var username = ""


    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activiy)

        title = resources.getString(R.string.detail_user)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        when (intent.getStringExtra(OPTION)) {
            "user" -> {
                val person = intent.getParcelableExtra<UserModel>(EXTRA_USER) as UserModel
                username = person.username.toString()
                val avatar = person.avatar
                setUserDetail(username, avatar)
            }
            "following" -> {
                val person = intent.getParcelableExtra<FollowingModel>(EXTRA_USER) as FollowingModel
                username = person.username.toString()
                val avatar = person.avatar
                setUserDetail(username, avatar)
            }
            "followers" -> {
                val person = intent.getParcelableExtra<FollowerModel>(EXTRA_USER) as FollowerModel
                username = person.username.toString()
                val avatar = person.avatar
                setUserDetail(username, avatar)
            }
        }

        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(HomeViewModel::class.java)

        showLoading(true)
        detailViewModel.getUserDetailFromAPI(username)
        showDetailViewModelObserver()

        loadData()
        viewPagerConfig()

        btn_favorite.setOnClickListener {
            saveData()
        }
    }

    private fun viewPagerConfig() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        sectionPagerAdapter.name = username
        view_pager.adapter = sectionPagerAdapter
        TabLayoutMediator(tabs, view_pager) { tab, position ->
            tab.text = resources.getString(tabTittles[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun loadData() {
        val sharedPreferences = this.getPreferences(Context.MODE_PRIVATE) ?: return
        val getStateFavorite = sharedPreferences.getBoolean("isFavorite", false)
        if(getStateFavorite) {
            isFavorite = true
            btn_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            isFavorite = false
            btn_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun saveData() {
        loadData()
        val sharedPreferences = this.getPreferences(Context.MODE_PRIVATE) ?: return
        if(isFavorite) {
            with(sharedPreferences.edit()) {
                putBoolean("isFavorite", false)
                commit()
            }
            btn_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            Toast.makeText(this, "$username ${resources.getString(R.string.added_favorite)}", Toast.LENGTH_SHORT).show()
        }else{
            with(sharedPreferences.edit()) {
                putBoolean("isFavorite", true)
                commit()
            }
            btn_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            Toast.makeText(this, "$username ${resources.getString(R.string.delete_faforite)}", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailViewModelObserver() {
        detailViewModel.getUser().observe(this, { userItems ->
            if (userItems != null) {
                Log.d("USER ITEMS", userItems.toString())

                if (userItems[0].fullname != "null") {
                    tv_fullname.text = userItems[0].fullname
                } else {
                    tv_fullname.text = resources.getString(R.string.not_fill_name)
                }

                if (userItems[0].location != "null") {
                    locationTv.text = userItems[0].location
                } else {
                    locationTv.text = resources.getString(R.string.not_fill_location)
                }

                if (userItems[0].company != "null") {
                    organizationTv.text = userItems[0].company
                } else {
                    organizationTv.text = resources.getString(R.string.not_fill_company)
                }

                if (userItems[0].email != "null") {
                    emailTv.text = userItems[0].email
                } else {
                    emailTv.text = resources.getString(R.string.not_fill_email)
                }

                if (userItems[0].bio != "null") {
                    bio.text = userItems[0].bio
                } else {
                    bio.text = resources.getString(R.string.not_fill_bio)
                }

                if (userItems[0].blog != "null") {
                    blogTv.text = userItems[0].blog
                } else {
                    blogTv.text = resources.getString(R.string.not_fill_blog)
                }

                publicRepo.text = userItems[0].repository.toString()

                showLoading(false)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setUserDetail(username: String?, avatar: String?) {
        tv_username.text = "@$username"
        Glide.with(this)
            .load(avatar)
            .placeholder(R.drawable.ic_baseline_face_24)
            .error(R.drawable.ic_baseline_face_24)
            .into(imageIv)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detail_progress_bar.visibility = View.VISIBLE
        } else {
            detail_progress_bar.visibility = View.GONE
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if(maxScrollSize == 0) {
            maxScrollSize = appBarLayout.totalScrollRange

            val percentage = (abs(verticalOffset)) * 100/maxScrollSize

            if(percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && isAvatarShown) {
                isAvatarShown = false

                imageIv.animate()
                    .scaleX(1F).scaleY(1F)
                    .start()
            }
        }
    }


}