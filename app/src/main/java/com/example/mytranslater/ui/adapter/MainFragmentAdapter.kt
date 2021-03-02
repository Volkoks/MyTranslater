package com.example.mytranslater.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytranslater.databinding.ItemRvMainFragmentBinding
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.utils.convertMeaningsToString

class MainFragmentAdapter(
    private val itemClickListener: IItemClickListener
) : RecyclerView.Adapter<MainFragmentAdapter.ViewHolder>() {

    var dataList: List<Word> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemRvMainFragmentBinding = ItemRvMainFragmentBinding.inflate(inflater, parent, false)
        return ViewHolder(itemRvMainFragmentBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner class ViewHolder(private val binding: ItemRvMainFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Word) {
            binding.tvItemRvWord.text = data.text
            binding.tvItemRvWordTranslate.text = data.meanings?.let { convertMeaningsToString(it) }
            binding.itemLayout.setOnClickListener {
                itemClickListener.onClick(data)
            }
        }

    }
}
