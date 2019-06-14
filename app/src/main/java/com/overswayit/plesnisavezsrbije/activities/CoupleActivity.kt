package com.overswayit.plesnisavezsrbije.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.binding.CoupleInfoViewBindings
import com.overswayit.plesnisavezsrbije.binding.CouplePointListViewBindings
import com.overswayit.plesnisavezsrbije.binding.CoupleRatingListViewBindings
import com.overswayit.plesnisavezsrbije.database.FakeCouple
import com.overswayit.plesnisavezsrbije.database.FakePointList
import com.overswayit.plesnisavezsrbije.database.FakeRatingList
import com.overswayit.plesnisavezsrbije.databinding.ActivityCoupleBinding
import com.overswayit.plesnisavezsrbije.models.Couple
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.viewmodels.*


class CoupleActivity : AppCompatActivity() {

    companion object {
        const val COUPLE_ID_KEY = "couple_id_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCoupleBinding = DataBindingUtil.setContentView(this, R.layout.activity_couple, ExampleDataComponent())

        val coupleid = intent.getStringExtra(COUPLE_ID_KEY)

        val pointListItemLA = getPointListItem(coupleid, DanceType.LA)
        val pointListItemST = getPointListItem(coupleid, DanceType.ST)
        val ratingListItemLA = getRatingListItem(coupleid, DanceType.LA)
        val ratingListItemST = getRatingListItem(coupleid, DanceType.ST)
        val ratingListItemKM = getRatingListItem(coupleid, DanceType.KM)

        val viewModelPointList = ViewModelProviders.of(this,
                CouplePointListViewModelFactory(this.application, pointListItemLA!!, pointListItemST!!)).get(CouplePointListViewModel::class.java)
        val viewModelRatingList = ViewModelProviders.of(this,
                CoupleRatingListViewModelFactory(this.application, ratingListItemLA!!, ratingListItemST!!, ratingListItemKM!!)).get(CoupleRatingListViewModel::class.java)
        val viewModelCoupleInfo = ViewModelProviders.of(this,
                CoupleInfoViewModelFactory(this.application, getCouple(coupleid)!!, pointListItemLA, pointListItemST)).get(CoupleInfoViewModel::class.java)
        binding.viewModelPointList = viewModelPointList
        binding.viewModelRatingList = viewModelRatingList
        binding.viewModelInfo = viewModelCoupleInfo

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.title = viewModelPointList.title
    }

    private fun getCouple(coupleid: String?): Couple? {
        return FakeCouple.getCoupleById(coupleid!!)
    }

    private fun getRatingListItem(coupleid: String?, danceType: DanceType): RatingListItem? {
        FakeRatingList.getRatingListByDanceType(danceType).forEach {
            if (coupleid == it.couple!!.id) {
                return it
            }
        }

        return null
    }

    private fun getPointListItem(coupleid: String?, danceType: DanceType): PointListItem? {
        FakePointList.getPointListByDanceType(danceType).forEach {
            if (coupleid == it.couple!!.id) {
                return it
            }
        }

        return null
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
