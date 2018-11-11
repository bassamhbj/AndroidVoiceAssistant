package com.hbjpro.androidvoiceassistant.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbjpro.androidvoiceassistant.adapter.NewsDataAdapter
import com.hbjpro.androidvoiceassistant.R
import com.hbjpro.androidvoiceassistant.common.data.Article
import com.hbjpro.androidvoiceassistant.presenter.FragmentPresenter
import kotlinx.android.synthetic.main.fragment_news_feed.*

class NewsFeedFragment : Fragment(), FragmentPresenter.ViewListener<List<Article>> {

    private val _presenter = FragmentPresenter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _presenter.doExecuteGetNewsFeed(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_news_feed, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewNews)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun setSnackBar(text: String){
        Snackbar.make(news_feed_layout, text, Snackbar.LENGTH_SHORT).show()
    }

    /* --- Override Methods ---*/
    override fun onSuccess(list: List<Article>) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = NewsDataAdapter(list, activity!!.applicationContext)
        setSnackBar("News Feed Headlines loaded")
    }

    override fun onError(errorMsg: String) {
        setSnackBar(errorMsg)
    }
}
