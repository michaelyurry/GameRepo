package com.yurry.gamerepo.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.yurry.core.data.Resource
import com.yurry.gamerepo.MainActivity
import com.yurry.gamerepo.R
import com.yurry.gamerepo.databinding.FragmentGameDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class GameDetailFragment: Fragment() {
    private val gameDetailViewModel: GameDetailViewModel by viewModel()

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!
    private var statusFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (requireActivity() as MainActivity).lockDrawer()
            val gameId: Int = arguments?.getInt("gameId", 0)!!
            gameDetailViewModel.isFavoriteGame(gameId).observe(viewLifecycleOwner, {
                statusFavorite = it
                setStatusFavorite(statusFavorite)
            })

            gameDetailViewModel.loadGameDetail(gameId).observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.fab.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            val gameDetail = it.data
                            if (gameDetail != null){
                                binding.progressBar.visibility = View.GONE
                                binding.toolbar.title = gameDetail.name
                                val desc = gameDetail.description
                                binding.content.tvDetailDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    Html.fromHtml(desc, Html.FROM_HTML_MODE_COMPACT)
                                } else {
                                    Html.fromHtml(desc)
                                }
                                Picasso.get()
                                    .load(gameDetail.background)
                                    .into(binding.ivDetailImage)

                                setStatusFavorite(statusFavorite)
                                binding.fab.setOnClickListener {
                                    statusFavorite = !statusFavorite
                                    gameDetailViewModel.setFavoriteGame(
                                        gameDetail,
                                        statusFavorite
                                    )
                                    setStatusFavorite(statusFavorite)
                                }
                                binding.fab.visibility = View.VISIBLE
                            }
                        }
                        is Resource.Error -> {
                            binding.fab.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE
                            binding.layoutError.root.visibility = View.VISIBLE
                            binding.layoutError.tvError.text =
                                it.message ?: getString(R.string.error)
                        }
                    }
                }
            })
        }
    }


    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(getDrawable(requireContext(), R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(getDrawable(requireContext(), R.drawable.ic_not_favorite_white))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}