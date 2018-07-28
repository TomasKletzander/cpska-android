package cz.dmn.cpska.ui.competition

import android.databinding.DataBindingUtil
import android.os.Bundle
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.databinding.ActivityCompetitionBinding
import cz.dmn.cpska.extensions.setTypeface
import cz.dmn.cpska.ui.BaseActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class CompetitionActivity : BaseActivity() {

    @Inject lateinit var competition: Competition
    private lateinit var binding: ActivityCompetitionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_competition)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setSubtitle(competition.name)
        binding.toolbar.setTypeface(resources.getString(R.string.fontDecorative))
    }
}