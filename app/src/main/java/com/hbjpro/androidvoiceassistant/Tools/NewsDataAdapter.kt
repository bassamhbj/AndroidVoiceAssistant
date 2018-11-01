package com.hbjpro.androidvoiceassistant.Tools

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbjpro.androidvoiceassistant.Data.Article
import com.hbjpro.androidvoiceassistant.R
import com.hbjpro.androidvoiceassistant.SubApplication
import kotlinx.android.synthetic.main.list_view_item.view.*

class NewsDataAdapter(val articlesList: List<Article>) : RecyclerView.Adapter<NewsDataAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(SubApplication.instance).inflate(R.layout.list_view_item,
                parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.textViewTitle.text = articlesList[position].title
        holder.itemView.textViewBody.text = articlesList[position].description

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}