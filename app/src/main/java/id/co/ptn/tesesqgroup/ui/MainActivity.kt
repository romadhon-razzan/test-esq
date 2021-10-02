package id.co.ptn.tesesqgroup.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import id.co.ptn.tesesqgroup.R
import id.co.ptn.tesesqgroup.bases.BaseActivity
import id.co.ptn.tesesqgroup.databinding.ActivityMainBinding
import id.co.ptn.tesesqgroup.ui.drink.HomeDrinkFragment

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeDrinkFragment>(R.id.container)
            }
        }

    }
}