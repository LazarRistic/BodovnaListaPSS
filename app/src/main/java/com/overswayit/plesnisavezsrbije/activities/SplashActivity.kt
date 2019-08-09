package com.overswayit.plesnisavezsrbije.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.overswayit.plesnisavezsrbije.MainActivity
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.fake.FakeNews
import com.overswayit.plesnisavezsrbije.models.AgeCategory
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import com.overswayit.plesnisavezsrbije.models.Filter
import com.overswayit.plesnisavezsrbije.networking.ListApiService
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import com.overswayit.plesnisavezsrbije.repository.NewsRepository
import com.overswayit.plesnisavezsrbije.viewmodels.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.overswayit.plesnisavezsrbije.R.layout.activity_splash)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            insertFiltersOnFirstTimeOpeningApp()
            fetchNews()

            if (viewModel.shouldFetchPointList()) {
                fetchPointList()
            }

            if (viewModel.shouldFetchRatingList()) {
                fetchRatingList()
            }

            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
        this@SplashActivity.startActivity(mainIntent)
        this@SplashActivity.finish()
    }

    @Suppress("SENSELESS_COMPARISON")
    private suspend fun insertFiltersOnFirstTimeOpeningApp() {
        val filterDao = AppDatabase.invoke(this).filterDao()
        if (filterDao.getAll() == null || filterDao.getAll().isNullOrEmpty()) {
            filterDao.insert(Filter(filterName = DanceCategory.I.asString(), active = true))
            filterDao.insert(Filter(filterName = DanceCategory.A.asString(), active = true))
            filterDao.insert(Filter(filterName = DanceCategory.B.asString(), active = true))
            filterDao.insert(Filter(filterName = DanceCategory.C.asString(), active = true))
            filterDao.insert(Filter(filterName = DanceCategory.D.asString(), active = true))
            filterDao.insert(Filter(filterName = AgeCategory.SENIOR.asString(), active = true))
            filterDao.insert(Filter(filterName = AgeCategory.ADULT.asString(), active = true))
            filterDao.insert(Filter(filterName = AgeCategory.YOUTH.asString(), active = true))
            filterDao.insert(Filter(filterName = AgeCategory.JUVENILE.asString(), active = true))
            filterDao.insert(Filter(filterName = AgeCategory.JUNIOR_I.asString(), active = true))
            filterDao.insert(Filter(filterName = AgeCategory.JUNIOR_II.asString(), active = true))
        }
    }

    private suspend fun fetchPointList() {
        val listRepository = ListRepository(application, ListApiService.LIST_API)
        listRepository.insertOrUpdate(listRepository.getLatestPointList())
    }

    private suspend fun fetchRatingList() {
        val listRepository = ListRepository(application, ListApiService.LIST_API)
        listRepository.insertOrUpdate(listRepository.getLatestRatingList())
    }

    private suspend fun fetchNews() = withContext(Dispatchers.IO) {
        val newsRepository = NewsRepository(application)
        FakeNews.getAllNews().forEach {
            newsRepository.insertOrUpdate(it)
        }
    }

    companion object {
        /** Duration of wait  */
        const val SPLASH_DISPLAY_LENGTH = 3000
    }
}
