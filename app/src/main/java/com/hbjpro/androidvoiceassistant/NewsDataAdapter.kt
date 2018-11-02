package com.hbjpro.androidvoiceassistant

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbjpro.androidvoiceassistant.Data.Article
import kotlinx.android.synthetic.main.list_view_item.view.*

class NewsDataAdapter(val articlesList: List<Article>, val context: Context) : RecyclerView.Adapter<NewsDataAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return articlesList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewTitle.text = articlesList[position].title
        holder.itemView.textViewBody.text = articlesList[position].description

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}