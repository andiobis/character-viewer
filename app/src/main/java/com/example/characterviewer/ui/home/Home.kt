package com.example.characterviewer.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.characterViewer.core.util.success
import com.example.characterviewer.MainActivityViewModel
import com.example.characterviewer.databinding.FragmentHomeBinding
import com.example.characterviewer.ui.adapters.CharactersAdapter
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class Home : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModel()
    private var binding : FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slidingPanel = binding?.slidingPaneLayout

        val adapter = CharactersAdapter {
            viewModel.updateCurrentCharacter(it)
            slidingPanel?.openPane()
        }

        binding?.recyclerView?.adapter= adapter

        viewModel.result.asLiveData().observe(viewLifecycleOwner) {
            it?.success { result ->
                viewModel.onNewCharacterList(result)
            }
        }

        viewModel.characterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        with( slidingPanel ?: return) {
            lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
            requireActivity().onBackPressedDispatcher.addCallback(HomeOnBackPressedCallBack(this))
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}