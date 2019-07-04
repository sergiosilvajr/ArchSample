package com.sergiosilvajr.archsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.sergiosilvajr.archsample.databinding.ActivityCounterBinding
import kotlinx.android.synthetic.main.activity_counter.*
import java.util.*

class MainActivity : AppCompatActivity(), CounterView {
    override fun warningMoreThan100() {
        Log.v("warningMoreThan100", "warningMoreThan100")

    }

    override fun showErrorPopup() {
        Log.v("showErrorPopup", "error")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCounterBinding>(this, R.layout.activity_counter)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        viewModel.view = this
        binding.viewModel = viewModel

        minus.setOnClickListener {
            viewModel.removeOne()
        }

        plus.setOnClickListener {
            viewModel.addOne()
        }
    }
}
