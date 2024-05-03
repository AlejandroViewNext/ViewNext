package com.example.viewnext.ui.Activity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.viewnext.ui.Activity.fragments.InstalacionFragment
import com.example.viewnext.ui.Activity.fragments.DetallesFragment
import com.example.viewnext.ui.Activity.fragments.EnergiaFragment

class PagerAdapter(fm: FragmentManager, behaviorResumeOnlyCurrentFragment: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InstalacionFragment()
            1 -> EnergiaFragment()
            2 -> DetallesFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {
            0 -> "Mi Instalación"
            1 -> "Energía"
            2 -> "Detalles"
            else -> null
        }
    }
}