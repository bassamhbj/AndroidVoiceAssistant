package com.hbjpro.androidvoiceassistant

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbjpro.androidvoiceassistant.data.Article
import com.hbjpro.androidvoiceassistant.presenter.FragmentPresenter

class NewsFeedFragment : Fragment(), FragmentPresenter.ListListener<Article> {

    private val _presenter = FragmentPresenter()
    private lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_news_feed, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewNews)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _presenter.doExecuteGetNewsFeed(this)
    }

    override fun onDetach() {
        super.onDetach()
    }


    // OVERRIDE METHODS
    override fun onSuccess(msg: String) {

    }

    override fun onLoadListSuccess(list: List<Article>) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = NewsDataAdapter(list, activity!!.applicationContext)
    }

    override fun onError(errorMsg: String) {

    }
}
