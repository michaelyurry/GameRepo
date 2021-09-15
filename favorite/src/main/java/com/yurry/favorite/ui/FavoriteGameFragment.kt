package com.yurry.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.yurry.core.ui.GameAdapter
import com.yurry.favorite.databinding.FragmentFavoriteBinding
import com.yurry.favorite.di.favoriteModule
import com.yurry.gamerepo.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteGameFragment: Fragment() {
    private val favoriteGameViewModel: FavoriteGameViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    init{
        loadKoinModules(favoriteModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val gameAdapter = GameAdapter()
            gameAdapter.onItemClick = { selectedData ->
                val bundle = bundleOf("gameId" to selectedData.gameId)
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_nav_favorite_to_nav_detail, bundle)
            }

            favoriteGameViewModel.favoriteGames.observe(viewLifecycleOwner, { games ->
                gameAdapter.setData(games)
                binding.layoutEmpty.root.visibility = if (games.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvFavGame) {
                layoutManager = LinearLayoutManager(context)
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