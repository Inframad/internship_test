package com.example.internshiptest.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.internshiptest.R
import com.example.internshiptest.databinding.NewsItemBinding
import com.example.internshiptest.domain.entity.Article
import com.squareup.picasso.Picasso

class ArticleViewHolder(
    private val binding: NewsItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, position: Int, onClickItem: (Int) -> Unit) {

        Picasso.get()
            .load(article.imageUrl)
            .placeholder(R.drawable.news_placeholder)
            .error(R.drawable.news_placeholder)
            .into(binding.newsIv)

        binding.newsTitleTv.text = article.title

        binding.root.setOnClickListener { onClickItem(position) }
    }
}
