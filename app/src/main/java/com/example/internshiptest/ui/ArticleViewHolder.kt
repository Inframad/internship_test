package com.example.internshiptest.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.internshiptest.R
import com.example.internshiptest.databinding.NewsItemBinding
import com.example.internshiptest.domain.entity.Article
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class ArticleViewHolder(
    private val binding: NewsItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onClickItem: () -> Unit) {

        Picasso.get()
            .load(article.imageUrl)
            .placeholder(R.drawable.image_placeholder)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(binding.newsIv)

        binding.newsTitleTv.text = article.title
    }
}
