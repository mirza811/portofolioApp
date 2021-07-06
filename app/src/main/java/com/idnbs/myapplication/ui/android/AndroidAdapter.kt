package com.idnbs.myapplication.ui.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.idnbs.myapplication.R
import com.idnbs.myapplication.data.android.Android
import com.idnbs.myapplication.databinding.ItemAndroidBinding

class AndroidAdapter: RecyclerView.Adapter<AndroidAdapter.AndroidViewHolder>() {

    private val listData = ArrayList<Android>()
    var onItemClicked: ((Android) -> Unit)? = null

    fun setData(newList: List<Android>?){
        if (newList == null) return
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndroidViewHolder =
        AndroidViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_android, parent, false)
        )

    override fun onBindViewHolder(holder: AndroidViewHolder, position: Int) {
        val listData = listData[position]
        holder.bind(listData)
    }

    override fun getItemCount(): Int = listData.size

    inner class AndroidViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemAndroidBinding.bind(itemView)

        fun bind(data: Android){
            binding.txtTitle.text = data.title
            binding.imgPoster.load(data.poster){
                crossfade(1000)
            }

        }

        init {
            binding.root.setOnClickListener {
                onItemClicked?.invoke(listData[adapterPosition])
            }
        }
    }
}