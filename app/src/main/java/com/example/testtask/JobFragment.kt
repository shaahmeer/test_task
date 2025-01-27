package com.example.testtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.adatper.Jobadapter
import com.example.testtask.data.addData
import com.example.testtask.databinding.FragmentJobBinding
import com.example.testtask.viewmodel.SharedViewModel

class JobFragment : Fragment() {
    private var _binding: FragmentJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var jobAdapter: Jobadapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter
        jobAdapter = Jobadapter(emptyList(), ::onFavoriteClick, ::onApplyClick)

        // Setup RecyclerView
        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.verticalRecyclerView.adapter = jobAdapter

        // Observe all jobs
        sharedViewModel.filteredJobs.observe(viewLifecycleOwner) { jobs ->
            jobAdapter.updateData(jobs)
        }

        // Initialize data (if not already initialized)
        sharedViewModel.setAllJobs(addData().joblist)

        // Setup SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { sharedViewModel.filterJobs(it) }
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                sharedViewModel.filterJobs(newText ?: "")
                return true
            }
        })
    }

    private fun onFavoriteClick(position: Int) {
        val job = jobAdapter.jobs[position]
        sharedViewModel.toggleFavorite(job) // Toggle favorite status in ViewModel
    }

    private fun onApplyClick(position: Int) {
        val job = jobAdapter.jobs[position]
        Toast.makeText(requireContext(), "Apply clicked for job: ${job.jobTitle}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
