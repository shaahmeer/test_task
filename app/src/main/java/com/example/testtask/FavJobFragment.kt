package com.example.testtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.testtask.databinding.FragmentFavJobBinding

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.adatper.FavJobAdapter
import com.example.testtask.data.Job
import com.example.testtask.viewmodel.SharedViewModel


class FavJobFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentFavJobBinding? = null
    private val binding get() = _binding!!

    private val favJobAdapter = FavJobAdapter(emptyList(), ::onFavoriteClick, ::onApplyClick, ::onRemoveFavoriteClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favJobAdapter
        }

        // Observe favorite jobs
        sharedViewModel.favoriteJobs.observe(viewLifecycleOwner) { favoriteJobs ->
            favJobAdapter.updateData(favoriteJobs) // Update the adapter with favorite jobs
        }
    }

    private fun onFavoriteClick(position: Int) {
        val job = favJobAdapter.jobs[position]
        sharedViewModel.toggleFavorite(job) // Toggle favorite status in ViewModel
    }

    private fun onApplyClick(position: Int) {
        val job = favJobAdapter.jobs[position]
        Toast.makeText(requireContext(), "Applied for ${job.jobTitle}", Toast.LENGTH_SHORT).show()
    }

    private fun onRemoveFavoriteClick(position: Int) {
        val job = favJobAdapter.jobs[position]
        sharedViewModel.removeFavoriteJob(job) // Remove from favorites in ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
