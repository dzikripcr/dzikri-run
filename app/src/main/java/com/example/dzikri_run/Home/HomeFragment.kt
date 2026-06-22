package com.example.dzikri_run.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzikri_run.Home.news.NewsAdapter
import com.example.dzikri_run.Home.pertemuan10.TenthActivity
import com.example.dzikri_run.Home.pertemuan2.SecondActivity
import com.example.dzikri_run.Home.pertemuan4.DashboardActivity
import com.example.dzikri_run.Home.pertemuan6.WebViewActivity
import com.example.dzikri_run.data.api.NewsApiClient
import com.example.dzikri_run.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "Home"

        setupRecyclerView()

        loadNews()

        binding.btnWebView.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    WebViewActivity::class.java
                )
            )
        }

        binding.btnToSecond.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    SecondActivity::class.java
                )
            )
        }

        binding.btnToTenth.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    TenthActivity::class.java
                )
            )
        }

        binding.btnToDashboard.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    DashboardActivity::class.java
                )
            )
        }

        binding.btnRefresh.setOnClickListener {
            loadNews()
        }
    }

    private fun setupRecyclerView() {

        newsAdapter = NewsAdapter(emptyList())

        binding.rvNews.apply {
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

            adapter = newsAdapter
        }
    }

    private fun loadNews() {

        viewLifecycleOwner.lifecycleScope.launch {

            try {

                val response =
                    NewsApiClient.apiService.getNews()

                Log.d(
                    "NEWS_SUCCESS",
                    response.toString()
                )

                newsAdapter.setData(response.data)

            } catch (e: Exception) {

                Log.e(
                    "NEWS_ERROR",
                    "Error mengambil berita",
                    e
                )

                Toast.makeText(
                    requireContext(),
                    "Gagal memuat berita",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}