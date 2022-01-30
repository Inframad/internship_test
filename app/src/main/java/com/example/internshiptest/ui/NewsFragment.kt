package com.example.internshiptest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.internshiptest.App
import com.example.internshiptest.databinding.FragmentNewsBinding
import com.example.internshiptest.presentation.state.NewsFragmentState
import com.example.internshiptest.presentation.viewmodel.NewsFragmentViewModel
import javax.inject.Inject

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsFragmentViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[NewsFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsListAdapter {}

        binding.apply {
            newsRv.adapter = adapter

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getLatestNews()
            }
        }

        viewModel.apply {
            state.observe(viewLifecycleOwner) { state ->
                updateUI(state)
            }

            news.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

    private fun updateUI(state: NewsFragmentState) {
        when (state) {
            NewsFragmentState.LOADING -> {
                binding.apply {
                    swipeRefreshLayout.isRefreshing = true
                }
            }
            NewsFragmentState.LOADED -> {
                binding.apply {
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }
}