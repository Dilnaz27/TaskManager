package com.geektech.taskmanager.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.taskmanager.databinding.ItemTaskBinding
import com.geektech.taskmanager.model.Quote
import com.geektech.taskmanager.model.Task
import com.geektech.taskmanager.ui.home.adapter.TaskAdapter

class QuoteAdapter: RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    private val data = arrayListOf<Quote>()

    fun addQuote (quote: List<Quote>) {
        data.clear()
        data.addAll(quote)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    inner class QuoteViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(quote: Quote) {
            with(binding) {
                tittle.text = quote.text
                description.text = quote.author
            }
        }
    }
}