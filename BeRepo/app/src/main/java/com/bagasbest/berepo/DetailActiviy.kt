package com.bagasbest.berepo

import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.berepo.adapter.SectionPagerAdapter
import com.bagasbest.berepo.database.DatabaseContract
import com.bagasbest.berepo.database.DatabaseContract.UserColumn.Companion.CONTENT_URI
import com.bagasbest.berepo.database.UserHelper
import com.bagasbest.berepo.model.FavoriteModel
import com.bagasbest.berepo.model.FollowerModel
import com.bagasbest.berepo.model.FollowingModel
import com.bagasbest.berepo.model.UserModel
import com.bagasbest.berepo.viewModel.HomeViewModel
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
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

    private val TAG = DetailActiviy::class.java.simpleName
    private lateinit var userHelper: UserHelper
    private lateinit var uriWithId: Uri
    private lateinit var detailViewModel: HomeViewModel
    private var maxScrollSize = 0
    private val PERCENTAGE_TO_ANIMATE_AVATAR = 20
    private var isAvatarShown = true
    private var username = ""
    private var avatar = ""
    private var url = ""
    private var id = ""

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
                avatar = person.avatar.toString()
                url = person.url.toString()
                id = person.id.toString()
                setUserDetail(username, avatar)
            }
            "following" -> {
                val person = intent.getParcelableExtra<FollowingModel>(EXTRA_USER) as FollowingModel
                username = person.username.toString()
                avatar = person.avatar.toString()
                url = person.url.toString()
                id = person.id.toString()
                setUserDetail(username, avatar)
            }
            "followers" -> {
                val person = intent.getParcelableExtra<FollowerModel>(EXTRA_USER) as FollowerModel
                username = person.username.toString()
                avatar = person.avatar.toString()
                url = person.url.toString()
                id = person.id.toString()
                setUserDetail(username, avatar)
            }
            "favorite" -> {
                val person = intent.getParcelableExtra<FavoriteModel>(EXTRA_USER) as FavoriteModel
                username = person.username.toString()
                avatar = person.avatar.toString()
                url = person.url.toString()
                id = person.id.toString()
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

        viewPagerConfig()

        //open database
        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()

        //set favorite user
        var favoriteStatus = false
        setFavoriteStatus(favoriteStatus)
        btn_favorite.setOnClickListener {
            if(!favoriteStatus) {
                val values = contentValuesOf(
                    DatabaseContract.UserColumn._ID to id,
                    DatabaseContract.UserColumn.USERNAME to username,
                    DatabaseContract.UserColumn.AVATAR to avatar,
                    DatabaseContract.UserColumn.URL to url
                )
                Log.d(TAG, values.toString())
                contentResolver.insert(CONTENT_URI, values)
                favoriteStatus = !favoriteStatus
                setFavoriteStatus(favoriteStatus)
                showSnackBar(resources.getString(R.string.user_added_to_favorite))
            } else {
                uriWithId = Uri.parse("$CONTENT_URI/$id")
                contentResolver.delete(uriWithId, null, null)
                favoriteStatus = !favoriteStatus
                setFavoriteStatus(favoriteStatus)
                showSnackBar(resources.getString(R.string.user_deleted_from_favorite))
            }
        }

        //database state
        val cursor: Cursor = userHelper.queryById(id)
        if(cursor.moveToNext()) {
            favoriteStatus = true
            setFavoriteStatus(favoriteStatus)
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(btn_favorite, msg, Snackbar.LENGTH_SHORT).show()
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

    private fun setFavoriteStatus(favoriteStatus: Boolean) {
        if(favoriteStatus) {
            btn_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            btn_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
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