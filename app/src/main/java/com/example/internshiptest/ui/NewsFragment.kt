package com.example.internshiptest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.internshiptest.App
import com.example.internshiptest.R
import com.example.internshiptest.databinding.FragmentNewsBinding
import com.example.internshiptest.presentation.state.NewsFragmentState
import com.example.internshiptest.presentation.viewmodel.NewsFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsFragmentViewModel

    private var snackbar: Snackbar? = null

    private val adapter = NewsListAdapter {
        val bundle = Bundle()
        bundle.putInt("Item position", it)
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, NewsDetailFragment::class.java, bundle, "NewsDetail")
            .addToBackStack("News")
            .commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory
            )[NewsFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            newsRv.adapter = adapter

            newsRv.adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.updateNews()
            }
        }

        viewModel.apply {
            state.observe(viewLifecycleOwner) { state ->
                updateUI(state)
            }

            lifecycle.coroutineScope.launch {
                news.collect { news ->
                    adapter.submitList(news)
                }
            }

        }
    }

    private fun showSnackbar(msg: String, actionName: String?, action: (View) -> Unit) {
        snackbar = Snackbar.make(
            binding.swipeRefreshLayout,
            msg,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(actionName, action)
        snackbar?.show()
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
            NewsFragmentState.OFFLINE_MODE -> {
                binding.swipeRefreshLayout.isRefreshing = false
                Toast.makeText(
                    context,
                    R.string.offline_mode_msg,
                    Toast.LENGTH_LONG
                ).show()
            }
            NewsFragmentState.UNKNOWN_ERROR -> {
                binding.swipeRefreshLayout.isRefreshing = false
                showSnackbar(
                    getString(R.string.unknown_error_msg),
                    getString(R.string.ok)
                ) {}
            }
            NewsFragmentState.SERVER_IS_NOT_AVAILABLE -> {
                binding.swipeRefreshLayout.isRefreshing = false
                showSnackbar(
                    getString(R.string.server_is_not_available_msg),
                    getString(R.string.ok)
                ) {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        snackbar?.dismiss()
        _binding = null
        snackbar = null
    }
}