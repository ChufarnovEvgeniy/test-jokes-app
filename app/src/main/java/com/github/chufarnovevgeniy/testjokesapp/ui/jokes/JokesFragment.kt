package com.github.chufarnovevgeniy.testjokesapp.ui.jokes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.chufarnovevgeniy.testjokesapp.app
import com.github.chufarnovevgeniy.testjokesapp.databinding.FragmentJokesBinding

class JokesFragment : Fragment() {
    private var _binding: FragmentJokesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JokesViewModel by viewModels {
        JokesViewModelFactory(app.jokesRepo)
    }

    private lateinit var adapter: JokesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initListener()
        observeValues()
    }

    private fun initRecyclerView() {
        adapter = JokesAdapter()
        binding.jokesRecyclerView.adapter = adapter
        binding.jokesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initListener() {
        binding.reloadButton.setOnClickListener {
            hideKeyboard()

            viewModel.onReloadClicked(
                binding.countEditText.text.toString()
            )
        }
    }

    private fun observeValues() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.contentLayout.children.forEach { it.isVisible = false }

            when (state) {
                is ViewState.Success -> {
                    adapter.updateData(state.jokes)
                    binding.jokesRecyclerView.isVisible = true
                }
                is ViewState.Loading -> {
                    binding.loadingLayout.isVisible = true
                }
                is ViewState.Error -> {
                    binding.errorMessageTextView.isVisible = true
                }
                is ViewState.Idle -> {
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.countEditText.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}