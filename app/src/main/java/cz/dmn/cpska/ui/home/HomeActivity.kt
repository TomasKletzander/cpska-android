package cz.dmn.cpska.ui.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import cz.dmn.cpska.R
import cz.dmn.cpska.databinding.ActivityHomeBinding
import cz.dmn.cpska.ui.BaseActivity
import dagger.android.AndroidInjection

class HomeActivity : BaseActivity() {

    lateinit var binding: ActivityHomeBinding

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigationProfile -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigationLeaderboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigationToday -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setSupportActionBar(binding.toolbar)

        binding.navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }
}
