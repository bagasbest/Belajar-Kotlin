package com.bagasbest.fundamental2.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderActivity
import com.bagasbest.fundamental2.academy.utils.DataDummy
import com.bagasbest.fundamental2.databinding.ActivityDetailCourseBinding
import com.bagasbest.fundamental2.databinding.ContentDetailCourseBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailCourseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private lateinit var binding: ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        binding = activityDetailCourseBinding.detailContent
        setContentView(activityDetailCourseBinding.root)

        setSupportActionBar(activityDetailCourseBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val extras = intent.extras
        if(extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if(courseId != null) {
                val modules = DataDummy.generateDummyModules(courseId)
                adapter.setModules(modules)
                for(course in DataDummy.generateDummyCourse()) {
                    if(course.courseId == courseId) {
                        populateCourse(course)
                    }
                }
            }
        }

        with(binding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

    }

    private fun populateCourse(course: CourseEntity) {
        binding.textTitle.text = course.title
        binding.textDesc.text = course.description
        binding.textDate.text = resources.getString(R.string.deadline_date, course.deadline)

        Glide.with(this)
            .load(course.imagePath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imagePoster)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, course.courseId)
            startActivity(intent)
        }
    }
}