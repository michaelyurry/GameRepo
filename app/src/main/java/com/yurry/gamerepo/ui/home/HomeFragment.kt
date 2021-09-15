package com.yurry.gamerepo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.yurry.core.data.Resource
import com.yurry.core.ui.GameAdapter
import com.yurry.gamerepo.R
import com.yurry.gamerepo.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val gameAdapter = GameAdapter()
            gameAdapter.onItemClick = { selectedData ->
                val bundle = bundleOf("gameId" to selectedData.gameId)
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_nav_home_to_nav_detail, bundle)
            }

            homeViewModel.game.observe(viewLifecycleOwner, { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            gameAdapter.setData(game.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.layoutError.root.visibility = View.VISIBLE
                            binding.layoutError.tvError.text =
                                game.message ?: getString(R.string.error)
                        }
                    }
                }
            })

            with(binding.rvGame) {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}