package com.bagasbest.fundamental2.academy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bagasbest.fundamental2.academy.data.source.remote.RemoteDataSource
import com.bagasbest.fundamental2.academy.utils.DataDummy
import com.bagasbest.fundamental2.academy.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.verify

class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponse[0].id
    private val moduleResponses = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourse() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadCourseCallback)
                .onAllCourseReceived(courseResponse)
            null
        }.`when`(remote).getAllCourses(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllCourse())
        verify(remote).getAllCourses(any())
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourse() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCourseCallback)
                .onAllCourseReceived(courseResponse)
            null
        }.`when`(remote).getAllCourses(any())

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourse())
        verify(remote).getAllCourses(any())
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())

    }

    @Test
    fun getCourseWithModule() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadCourseCallback)
                .onAllCourseReceived(courseResponse)
            null
        }.`when`(remote).getAllCourses(any())

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithModule(courseId))

        verify(remote).getAllCourses(any())

        assertNotNull(courseEntities)
        assertNotNull(courseEntities.title)
        assertEquals(courseResponse[0].title, courseEntities.title)
    }

    @Test
    fun getAllModuleByCourse() {
        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadModulesCallback)
                .onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(eq(courseId), any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllModuleByCourse(courseId))
        verify(remote).getModules(eq(courseId), any())
        assertNotNull(courseEntities)
        assertEquals(moduleResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadModulesCallback)
                .onAllModulesReceived(moduleResponses)
        }.`when`(remote).getModules(eq(courseId), any())

        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadContentCallback)
                .onContentReceived(content)
            null
        }.`when`(remote).getContent(eq(moduleId), any())
        val resultModule = LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))
        verify(remote).getModules(eq(courseId), any())
        verify(remote).getContent(eq(moduleId), any())
        assertNotNull(resultModule)
        assertEquals(content.content, resultModule.contentEntity?.content)

        assertNotNull(resultModule)
        assertNotNull(resultModule.contentEntity)
        assertNotNull(resultModule.contentEntity?.content)
        assertEquals(content.content, resultModule.contentEntity?.content)
    }
}