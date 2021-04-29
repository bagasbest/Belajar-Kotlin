package com.bagasbest.fundamental2.academy.ui.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.academy.ui.reader.content.ModuleContentFragment
import com.bagasbest.fundamental2.academy.ui.reader.list.ModuleListFragment
import com.bagasbest.fundamental2.academy.viewmodel.ViewModelFactory
import com.bagasbest.fundamental2.databinding.ActivityCourseReaderBinding

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {

    companion object {
        val EXTRA_COURSE_ID = "extra_course_id"
    }

    private lateinit var binding: ActivityCourseReaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[CourseReaderViewModel::class.java]

        val bundle = intent.extras
        if(bundle != null) {
            val courseId = bundle.getString(EXTRA_COURSE_ID)
            if(courseId != null) {
                viewModel.setSelectedCourse(courseId)
                populateFragment()
            }
        }

    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_container, fragment, ModuleContentFragment.TAG)
            .addToBackStack(null)
            .commit()
    }


    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
        if(fragment == null) {
            fragment = ModuleListFragment.newInstance()
            fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.TAG)
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }



}