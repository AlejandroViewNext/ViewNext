package com.example.viewnext.ui.Activity.Practicas.Practica2

// SmartSolar_Activity.kt
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.Principal_Activity
import com.example.viewnext.ui.Activity.adapter.PagerAdapter
import com.example.viewnext.ui.Activity.viewmodel.practica2.SmartSolarViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmartSolar_Activity : AppCompatActivity() {

    private val viewModel: SmartSolarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.smart_solar)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)

        val pagerAdapter = PagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)

        toolbar.setNavigationOnClickListener {
            viewModel.onBackPressed()
        }

        viewModel.navigateToMainActivity.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, Principal_Activity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                })
                finish()
            }
        })
    }
}
