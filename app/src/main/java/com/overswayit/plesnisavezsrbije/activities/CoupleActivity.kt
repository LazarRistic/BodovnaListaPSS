package com.overswayit.plesnisavezsrbije.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.binding.CoupleInfoViewBindings
import com.overswayit.plesnisavezsrbije.binding.CouplePointListViewBindings
import com.overswayit.plesnisavezsrbije.binding.CoupleRatingListViewBindings
import com.overswayit.plesnisavezsrbije.database.fake.FakeCouple
import com.overswayit.plesnisavezsrbije.database.fake.FakePointList
import com.overswayit.plesnisavezsrbije.database.fake.FakeRatingList
import com.overswayit.plesnisavezsrbije.databinding.ActivityCoupleBinding
import com.overswayit.plesnisavezsrbije.models.Couple
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.viewmodels.*


class CoupleActivity : BaseActivity() {

    companion object {
        const val COUPLE_ID_KEY = "couple_id_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCoupleBinding = DataBindingUtil.setContentView(this, R.layout.activity_couple, ExampleDataComponent())

        val coupleid = intent.getStringExtra(COUPLE_ID_KEY)

        val viewModelPointList = ViewModelProviders.of(this,
                CouplePointListViewModelFactory(this.application, coupleid)).get(CouplePointListViewModel::class.java)
        val viewModelRatingList = ViewModelProviders.of(this,
                CoupleRatingListViewModelFactory(this.application, coupleid)).get(CoupleRatingListViewModel::class.java)
        val viewModelCoupleInfo = ViewModelProviders.of(this,
                CoupleInfoViewModelFactory(this.application, coupleid)).get(CoupleInfoViewModel::class.java)
        binding.viewModelPointList = viewModelPointList
        binding.viewModelRatingList = viewModelRatingList
        binding.viewModelInfo = viewModelCoupleInfo

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        lifecycleScope.launchWhenResumed {
            toolbar.title = viewModelPointList.title
        }
    }

    private fun getCouple(coupleid: String?): Couple? {
        return FakeCouple.getCoupleById(coupleid!!)
    }

    private inner class ExampleDataComponent : DataBindingComponent {
        override fun getCouplePointListViewBindings(): CouplePointListViewBindings {
            return CouplePointListViewBindings()
        }

        override fun getCoupleRatingListViewBindings(): CoupleRatingListViewBindings {
            return CoupleRatingListViewBindings()
        }

        override fun getCoupleInfoViewBindings(): CoupleInfoViewBindings {
            return CoupleInfoViewBindings()
        }
    }
}
