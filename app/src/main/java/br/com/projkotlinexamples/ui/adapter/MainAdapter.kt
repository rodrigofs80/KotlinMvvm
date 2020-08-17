package br.com.projkotlinexamples.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import br.com.projkotlinexamples.databinding.ListItemBinding
import br.com.projkotlinexamples.ui.fragment.MainFragmentDirections
import br.com.projkotlinexamples.ui.model.Materia
import br.com.projkotlinexamples.util.toTransitionGroup

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val items = mutableListOf<Materia>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(list: List<Materia>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class MainViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(materia: Materia) {
            binding.clickListener = View.OnClickListener {
                val destination = MainFragmentDirections.actionMainToDetail(materia)
                val extras = FragmentNavigatorExtras(
                    binding.name.toTransitionGroup(),
                    binding.avatar.toTransitionGroup(),
                    binding.description.toTransitionGroup()
                )
                it.findNavController().navigate(destination, extras)
            }
            binding.materia = materia
            binding.executePendingBindings()
        }
    }
}