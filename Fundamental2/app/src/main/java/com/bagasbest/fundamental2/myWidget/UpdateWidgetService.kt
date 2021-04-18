package com.bagasbest.fundamental2.myWidget

import android.app.job.JobParameters
import android.app.job.JobService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.widget.RemoteViews
import com.bagasbest.fundamental2.R

class UpdateWidgetService : JobService() {
    override fun onStartJob(jobParameters: JobParameters): Boolean{
        val managet = AppWidgetManager.getInstance(this)
        val view = RemoteViews(packageName, R.layout.random_number_widget)
        val theWidget = ComponentName(this, RandomNumberWidget::class.java)
        val lastUpdate = "Random: " + NumberGenerator.generate(100)
        view.setTextViewText(R.id.appwidget_text, lastUpdate)
        managet.updateAppWidget(theWidget, view)
        jobFinished(jobParameters, false)
        return true
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean = false
}