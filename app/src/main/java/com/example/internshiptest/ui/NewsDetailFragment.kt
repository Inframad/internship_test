package com.example.internshiptest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.internshiptest.App
import com.example.internshiptest.databinding.FragmentNewsDetailBinding
import com.example.internshiptest.presentation.viewmodel.NewsFragmentViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class NewsDetailFragment: Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding: FragmentNewsDetailBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsFragmentViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[NewsFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("Item position")

        lifecycleScope.launch {
            val article = viewModel.news.first()[position!!]

            binding.apply {
                Picasso.get().load(article.imageUrl).into(newsIv)
                newsTitleTv.text = article.title
                newsDescriptionTv.text = article.description
                newsPublishedDateTv.text = article.date.format(DateTimeFormatter.ofPattern("dd.MM.yy hh:mm"))
                newsAuthorTv.text = article.author
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}