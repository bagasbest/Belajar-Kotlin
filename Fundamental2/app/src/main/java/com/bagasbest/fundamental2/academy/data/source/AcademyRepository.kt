package com.bagasbest.fundamental2.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bagasbest.fundamental2.academy.data.ContentEntity
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.data.ModuleEntity
import com.bagasbest.fundamental2.academy.data.source.remote.RemoteDataSource
import com.bagasbest.fundamental2.academy.data.source.remote.response.ContentResponse
import com.bagasbest.fundamental2.academy.data.source.remote.response.CourseResponse
import com.bagasbest.fundamental2.academy.data.source.remote.response.ModuleResponse

class AcademyRepository
private constructor(private val remoteDataSource: RemoteDataSource) : AcademyDataSource {

    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteData: RemoteDataSource): AcademyRepository =
            instance ?: synchronized(this) {
                instance ?: AcademyRepository(remoteData).apply {
                    instance = this
                }
            }
    }


    override fun getAllCourse(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCourseCallback {
            val courseList = ArrayList<CourseEntity>()

            override fun onAllCourseReceived(courseResponses: List<CourseResponse>) {
                for (response in courseResponses) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }

        })

        return courseResults
    }


    override fun getBookmarkedCourse(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()

        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCourseCallback {
            override fun onAllCourseReceived(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()

                for (response in courseResponses) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }
        })
        return courseResults
    }


    override fun getCourseWithModule(courseId: String): LiveData<CourseEntity> {
        val courseResults = MutableLiveData<CourseEntity>()

        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCourseCallback {
            override fun onAllCourseReceived(courseResponses: List<CourseResponse>) {
                lateinit var course: CourseEntity

                for (response in courseResponses) {
                    if (response.id == courseId) {
                        course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath
                        )
                    }
                }
                courseResults.postValue(course)
            }
        })

        return courseResults
    }

    override fun getAllModuleByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResults = MutableLiveData<List<ModuleEntity>>()

        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()

                for (response in moduleResponses) {
                    val course = ModuleEntity(
                        response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false
                    )
                    moduleList.add(course)
                }
                moduleResults.postValue(moduleList)
            }

        })

        return moduleResults
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResult = MutableLiveData<ModuleEntity>()

        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                lateinit var module: ModuleEntity

                for (response in moduleResponses) {
                    if (response.moduleId == moduleId) {
                        module = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false
                        )
                        remoteDataSource.getContent(
                            moduleId,
                            object : RemoteDataSource.LoadContentCallback {
                                override fun onContentReceived(contentResponses: ContentResponse) {
                                    module.contentEntity =
                                        ContentEntity(contentResponses.content)
                                    moduleResult.postValue(module)
                                }
                            })
                        break
                    }
                }
            }
        })

        return moduleResult
    }

}