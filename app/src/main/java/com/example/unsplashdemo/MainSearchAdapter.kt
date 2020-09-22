package com.example.unsplashdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashdemo.api.objUnSplash.gson.search_image.Result
import com.example.unsplashdemo.databinding.ListItemBinding
import com.example.unsplashdemo.fragment.onItemListener

class MainSearchAdapter(val listener: onItemListener) :
    PagingDataAdapter<Result, MainSearchAdapter.SearchViewHolder>(DataDiff) {

    lateinit var itemBinding: ListItemBinding
    private var mContext: Context? = null

    inner class SearchViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindView(result: Result) {
            Glide.with(mContext!!).load(result.urls?.regular)
                .into(binding.itemImage)

            binding.root.setOnClickListener {
                listener.onClickItem(result.urls?.regular!!)
            }
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBindView(item!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        mContext = parent.context
        itemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.list_item,
            parent,
            false
        )
        return SearchViewHolder(itemBinding)
    }
}

object DataDiff : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}
