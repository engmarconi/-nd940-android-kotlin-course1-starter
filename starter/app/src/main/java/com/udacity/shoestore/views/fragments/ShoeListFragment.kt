package com.udacity.shoestore.views.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModels.ShoeViewModel
import kotlinx.android.synthetic.main.item_shoe.view.*

class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding
    lateinit var viewModel: ShoeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        binding.addShoeButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragment2ToShoeDetailsFragment())
        }

        var args = ShoeListFragmentArgs.fromBundle(requireArguments())
        if (args.newShoe != null) {
            viewModel.addNewShoe(args.newShoe!!)
        }
        viewModel.shoesList.observe(viewLifecycleOwner, Observer {
            binding.shoeListLayout.removeAllViews()
            for (item in it) {
                addItem(item)
            }
        })
        return binding.root
    }

    private fun addItem(model: Shoe) {
        var child = layoutInflater.inflate(R.layout.item_shoe, binding.shoeListLayout, false);
        child.showName_TextView.text = model.name
        child.showSize_TextView.text = model.size.toString()
        child.showDescription_TextView.text = model.description
        binding.shoeListLayout.addView(child);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}