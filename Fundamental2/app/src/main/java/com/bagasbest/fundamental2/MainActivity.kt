package com.bagasbest.fundamental2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.fundamental2.academy.ui.home.HomeActivity
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.myAlarmManager.MyAlarmManagerMainActivity
import com.bagasbest.fundamental2.myBackgroundThread.MyBackgroundThreadMainActivity
import com.bagasbest.fundamental2.myBroadcastReceiver.MyBroadcastReceiverMainActivity
import com.bagasbest.fundamental2.myDeepNavigation.MyDeepNavigationMainActivity
import com.bagasbest.fundamental2.myJobSchduler.MyJobSchdulerMainActivity
import com.bagasbest.fundamental2.myLocalization.MyLocalizationMainActivity
import com.bagasbest.fundamental2.myNotesApp.MyNotesAppMainActivity
import com.bagasbest.fundamental2.myPreLoadData.MyPreloadDataMainActivity
import com.bagasbest.fundamental2.myQuote.MyQuoteMainActivity
import com.bagasbest.fundamental2.myReadWriteFile.MyReadWriteMainActivity
import com.bagasbest.fundamental2.myService.MyServiceMainActivity
import com.bagasbest.fundamental2.mySettingPref.MySettingPrefMainActivity
import com.bagasbest.fundamental2.mySharedPreferences.MySharedPrefMainActivity
import com.bagasbest.fundamental2.myTabLayout.MyTabLayoutMainActivity
import com.bagasbest.fundamental2.myUnitTest.MyUnitTestMainActivity
import com.bagasbest.fundamental2.myViewModel.MyViewModelMainActivity
import com.bagasbest.fundamental2.myViewModel2.MyViewModel2MainActivity
import com.bagasbest.fundamental2.myWebView.MyWebViewMainActivity
import com.bagasbest.fundamental2.myWidget.MyWidgetMainActivity
import com.bagasbest.fundamental2.myWorkManager.MyWorkManagerMainActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnClickListener {
            startActivity(Intent(this, MyNavbar::class.java))
        }

        binding.tabLayout.setOnClickListener {
            startActivity(Intent(this, MyTabLayoutMainActivity::class.java))
        }

        binding.localization.setOnClickListener {
            startActivity(Intent(this, MyLocalizationMainActivity::class.java))
        }

        binding.testing.setOnClickListener{
            startActivity(Intent(this, MyUnitTestMainActivity::class.java))
        }

        binding.backgroundThread.setOnClickListener {
            startActivity(Intent(this, MyBackgroundThreadMainActivity::class.java))
        }

        binding.service.setOnClickListener {
            startActivity(Intent(this, MyServiceMainActivity::class.java))
        }

        binding.broadcastReceiver.setOnClickListener {
            startActivity(Intent(this, MyBroadcastReceiverMainActivity::class.java))
        }

        binding.alarmManager.setOnClickListener {
            startActivity(Intent(this, MyAlarmManagerMainActivity::class.java))
        }

        binding.webApi.setOnClickListener {
            startActivity(Intent(this, MyQuoteMainActivity::class.java))
        }

        binding.jobScheduler.setOnClickListener {
            startActivity(Intent(this, MyJobSchdulerMainActivity::class.java))
        }

        binding.workManager.setOnClickListener {
            startActivity(Intent(this, MyWorkManagerMainActivity::class.java))
        }

        binding.deepLink.setOnClickListener {
            startActivity(Intent(this, MyDeepNavigationMainActivity::class.java))
        }

        binding.viewModel.setOnClickListener {
            startActivity(Intent(this, MyViewModelMainActivity::class.java))
        }

        binding.viewModel2.setOnClickListener {
            startActivity(Intent(this, MyViewModel2MainActivity::class.java))
        }

        binding.academy.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.myWriteAndRead.setOnClickListener {
            startActivity(Intent(this, MyReadWriteMainActivity::class.java))
        }

        binding.sharedPref.setOnClickListener {
            startActivity(Intent(this, MySharedPrefMainActivity::class.java))
        }

        binding.settingPref.setOnClickListener {
            startActivity(Intent(this, MySettingPrefMainActivity::class.java))
        }

        binding.readWrite.setOnClickListener {
            startActivity(Intent(this, MyNotesAppMainActivity::class.java))
        }

        binding.myPreloadData.setOnClickListener {
            startActivity(Intent(this, MyPreloadDataMainActivity::class.java))
        }

        binding.myWidget.setOnClickListener{
            startActivity(Intent(this, MyWidgetMainActivity::class.java))
        }
        binding.myWebView.setOnClickListener {
            startActivity(Intent(this, MyWebViewMainActivity::class.java))
        }
    }


}