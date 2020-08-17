package br.com.projkotlinexamples.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.projkotlinexamples.databinding.MainFragmentBinding
import br.com.projkotlinexamples.util.waitForTransition
import br.com.projkotlinexamples.ui.adapter.MainAdapter
import br.com.projkotlinexamples.vm.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()
    private val myAdapter = MainAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = myAdapter
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = this
        viewModel.lista.observe(viewLifecycleOwner, Observer {
            it?.let {
                myAdapter.submitList(viewModel.lista.value!!)
            }
        })
        waitForTransition(binding.recycler)
    }
}