package com.hbjpro.androidvoiceassistant

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


import android.support.v7.widget.RecyclerView
import com.hbjpro.androidvoiceassistant.Data.Post
import kotlinx.android.synthetic.main.list_view_item.view.*

/*class ListViewAdapter (var list: MutableList<String>) : BaseAdapter() {

    var inflater : LayoutInflater? = null

    init {
        inflater = LayoutInflater.from(SubApplication.instance)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        view = inflater?.inflate(R.layout.list_view_item, parent, false)

        var viewHolder = ViewHolder(view)
        viewHolder.setText(list.get(position))

        return view!!
    }

    override fun getItem(position: Int): String? {
        return list?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list!!.size
    }
}

private class ViewHolder(var view: View?){

    var root: View? = null

    init {
        this.root = view
    }

    fun setText(text: String?){
        //var numberText = root?.findViewById(R.id.txt_number) as TextView
        //var infoText = root?.findViewById(R.id.info_text) as TextView

        //infoText.text = text
    }
}*/

class PostItemAdapter(val postList: List<Post>, val context: Context) :
        RecyclerView.Adapter<PostItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_view_item,
                parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.textViewTitle.text = postList.get(position).title
        holder.itemView.textViewBody.text = postList.get(position).body

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}