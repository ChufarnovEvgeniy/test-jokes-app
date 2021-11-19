package com.github.chufarnovevgeniy.testjokesapp.ui.api_info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.github.chufarnovevgeniy.testjokesapp.BuildConfig
import com.github.chufarnovevgeniy.testjokesapp.databinding.FragmentApiInfoBinding

class ApiInfoFragment : Fragment() {
    private var _binding: FragmentApiInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWebViewSettings()

        if (savedInstanceState == null) {
            binding.apiInfoWebView.loadUrl(BuildConfig.API_INFO_URL)
        }

        handleOnBackPressed()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        binding.apiInfoWebView.webViewClient = ApiInfoWebViewClient()

        binding.apiInfoWebView.settings.domStorageEnabled = true
        binding.apiInfoWebView.settings.loadsImagesAutomatically = true
        binding.apiInfoWebView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        binding.apiInfoWebView.settings.javaScriptEnabled = true
    }

    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.apiInfoWebView.canGoBack()) {
                        binding.apiInfoWebView.goBack()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}