package com.example.unsplashdemo.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashdemo.R
import com.example.unsplashdemo.api.objUnSplash.UnSplash
import kotlinx.android.synthetic.main.list_item.view.*

class MainListAdapter : PagingDataAdapter<UnSplash, MainListAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewName.text =
            "${getItem(position)?.altDescription} ${getItem(position)?.urls?.small}"
        holder.itemView.textViewEmail.text = getItem(position)?.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<UnSplash>() {

        override fun areItemsTheSame(oldItem: UnSplash, newItem: UnSplash): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnSplash, newItem: UnSplash): Boolean {
            return oldItem == newItem
        }
    }

}
