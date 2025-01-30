package com.example.testtask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.databinding.FragmentFavJobBinding
import com.example.testtask.adatper.FavJobAdapter
import com.example.testtask.viewmodel.SharedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        // Set up RecyclerView
        binding.favRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favJobAdapter
        }
        sharedViewModel.favoriteJobs.observe(viewLifecycleOwner) { favoriteJobs ->
            Log.d("FavJobFragment", "Favorite Jobs: ${favoriteJobs.size}")
        }


        // Observe favoriteJobs and update RecyclerView and badge
        sharedViewModel.favoriteJobs.observe(viewLifecycleOwner) { favoriteJobs ->
            if (favoriteJobs != null) {
                favJobAdapter.updateData(favoriteJobs)
                updateFavoritesBadge(favoriteJobs.size) // Update the badge count
            }
        }
    }

    private fun updateFavoritesBadge(favoriteCount: Int) {
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val menuItem = bottomNavigationView.menu.findItem(R.id.navigation_favorites) // Use the correct menu ID

        val badge = bottomNavigationView.getOrCreateBadge(menuItem.itemId) // Get or create a badge
        if (favoriteCount > 0) {
            badge.isVisible = true
            badge.number = favoriteCount // Set the count
        } else {
            badge.isVisible = false // Hide the badge if no favorites
        }
    }

    private fun onFavoriteClick(position: Int) {
        val job = favJobAdapter.jobs[position]
        sharedViewModel.toggleFavorite(job) // Toggle favorite status in ViewModel
    }

    private fun onApplyClick(position: Int) {
        val job = favJobAdapter.jobs[position]
        Toast.makeText(requireContext(), "Apply clicked for job: ${job.jobTitle}", Toast.LENGTH_SHORT).show()
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
