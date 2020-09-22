package com.example.unsplashdemo.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashdemo.R
import com.example.unsplashdemo.api.objUnSplash.UnSplash
import com.example.unsplashdemo.databinding.ListItemBinding

class MainListAdapter(val listener: onItemListener) :
    PagingDataAdapter<UnSplash, MainListAdapter.ItemUnSplatViewHolder>(DataDifferntiator) {

    lateinit var itemBinding: ListItemBinding
    private var mContext: Context? = null

    inner class ItemUnSplatViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(unSplash: UnSplash) {
            Glide.with(mContext!!).load(unSplash.urls?.small)
                .into(binding.itemImage)

            binding.root.setOnClickListener {
                listener.onClickItem(unSplash.urls?.small!!)
            }
        }
    }

    override fun onBindViewHolder(holder: ItemUnSplatViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUnSplatViewHolder {
        mContext = parent.context
        itemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.list_item,
            parent,
            false
        )
        return ItemUnSplatViewHolder(itemBinding)
    }

}

object DataDifferntiator : DiffUtil.ItemCallback<UnSplash>() {

    override fun areItemsTheSame(oldItem: UnSplash, newItem: UnSplash): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnSplash, newItem: UnSplash): Boolean {
        return oldItem == newItem
    }
}

interface onItemListener {
    fun onClickItem(string: String)
}
