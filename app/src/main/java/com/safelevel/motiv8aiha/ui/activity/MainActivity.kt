package com.safelevel.motiv8aiha.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.safelevel.motiv8aiha.R
import com.safelevel.motiv8aiha.ui.adapter.FeedRecyclerAdapter
import com.safelevel.motiv8aiha.ui.customview.ShakeItemsLinearLayoutManager
import com.safelevel.motiv8aiha.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeFeed()
        initFeed()
        setButtonClick()
        observeFilter()
    }

    private fun setButtonClick() {
        startStopButton.setOnClickListener {
            if (viewModel.isSocketRunning()) {
                startStopButton.text = getString(R.string.start)
                viewModel.stopSocket()
            } else {
                viewModel.startSocket()
                startStopButton.text = getString(R.string.stop)
            }
        }
    }

    private fun observeFilter() {
        filterEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    try {
                        val weight = p0.toString().toDouble()
                        viewModel.setFilter(weight)
                    } catch (e: Exception) {
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun observeFeed() {
        viewModel.listenToFeed().observe(this, {
            appBarTitle.setText(it.name)
            (feedRecycler.adapter as FeedRecyclerAdapter).addItem(it)
            feedRecycler.scrollToPosition(0)
        })

        viewModel.listenToCachedFeedItems().observe(this, {
            (feedRecycler.adapter as FeedRecyclerAdapter).addItems(it)
            feedRecycler.scrollToPosition(0)
            viewModel.clearCachingList()
        })
    }

    private fun initFeed() {
        val adapter = FeedRecyclerAdapter(arrayListOf())
        feedRecycler.layoutManager = ShakeItemsLinearLayoutManager(this, RecyclerView.VERTICAL)
        feedRecycler.adapter = adapter
    }
}