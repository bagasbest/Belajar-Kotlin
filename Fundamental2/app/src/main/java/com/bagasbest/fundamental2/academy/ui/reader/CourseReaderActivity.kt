package com.bagasbest.fundamental2.academy.ui.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bagasbest.fundamental2.databinding.ActivityCourseReaderBinding

class CourseReaderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCourseReaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}