package com.example.viewnext.ui.Activity.Practicas.Practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.viewnext.ui.Activity.adapter.PagerAdapter
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.Principal
import com.google.android.material.appbar.MaterialToolbar

class SmartSolar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.smart_solar)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Obtener TabLayout y ViewPager
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)

        // Crear un adaptador para manejar los fragmentos en los tabs
        val pagerAdapter = PagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = pagerAdapter

        // Vincular ViewPager con TabLayout
        tabLayout.setupWithViewPager(viewPager)

        // Configurar el clic en la Toolbar para redirigir a la actividad "Principal"
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, Principal::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}
