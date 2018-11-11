package com.hbjpro.androidvoiceassistant.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbjpro.androidvoiceassistant.R
import com.hbjpro.androidvoiceassistant.common.data.MessageData
import kotlinx.android.synthetic.main.news_feed_item.view.*

class MessageAdapter(val messageList: List<MessageData?>, val context: Context) : RecyclerView.Adapter<MessageAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.message_item, parent, false))
    }

    override fun getItemCount(): Int {
        return messageList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(messageList[position] != null){
            holder.itemView.textViewTitle.text = messageList[position]?.id
            holder.itemView.textViewBody.text = messageList[position]?.content
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}