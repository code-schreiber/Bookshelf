package com.toolslab.bookshelf.view.bookshelf


import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.toolslab.bookshelf.R
import com.toolslab.bookshelf.model.BookViewModel

class BookshelfAdapter(private val viewModels: List<BookViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val headerText: TextView = view.findViewById(R.id.item_book_header_text)
    }

    class RowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val title: TextView = view.findViewById(R.id.item_book_title)
        internal val author: TextView = view.findViewById(R.id.item_book_author)
        internal val description: TextView = view.findViewById(R.id.item_book_description)
        internal val date: TextView = view.findViewById(R.id.item_book_date)
        internal val background: View = view.findViewById(R.id.item_book_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
            RowViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val viewModel = viewModels[position]
        if (viewModel.isHeader()) {
            val headerViewHolder = viewHolder as HeaderViewHolder
            headerViewHolder.headerText.text = viewModel.headerText
        } else {
            val rowViewHolder = viewHolder as RowViewHolder
            rowViewHolder.title.text = viewModel.title
            rowViewHolder.author.text = viewModel.author
            rowViewHolder.description.text = viewModel.description
            rowViewHolder.date.text = viewModel.date
            rowViewHolder.background.background = ColorDrawable(viewModel.color)
        }
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    override fun getItemViewType(position: Int): Int {
        val viewModel = viewModels[position]
        return if (viewModel.isHeader()) HEADER else ROW
    }

    companion object {
        private const val HEADER = 0
        private const val ROW = 1
    }

}
