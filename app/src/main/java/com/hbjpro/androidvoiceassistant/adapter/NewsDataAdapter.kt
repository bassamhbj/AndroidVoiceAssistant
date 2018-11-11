package com.hbjpro.androidvoiceassistant.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbjpro.androidvoiceassistant.R
import com.hbjpro.androidvoiceassistant.common.data.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_feed_item.view.*

class NewsDataAdapter(val articlesList: List<Article>, val context: Context) : RecyclerView.Adapter<NewsDataAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_feed_item, parent, false))
    }

    override fun getItemCount(): Int {
        return articlesList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(articlesList[position].urlToImage).resize(600, 200).centerInside().into(holder.itemView.imageViewCover)
        holder.itemView.textViewTitle.text = articlesList[position].title
        holder.itemView.textViewBody.text = getPreviewBody(if(articlesList[position].description == null) "" else articlesList[position].description.toString())
    }

    private fun getPreviewBody(body: String): String{
        return if (body.length > 100) body.substring(0, 100) + "..." else body
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}