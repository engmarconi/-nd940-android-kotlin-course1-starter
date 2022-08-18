package com.udacity.shoestore.views.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModels.ShoeViewModel

class ShoeDetailsFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailsBinding
    lateinit var viewModel : ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        binding.cancelButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(
                    ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment2(
                        null
                    )
                )
        }
        binding.saveButton.setOnClickListener { view ->
            if(!viewModel.isDataValid())
            {
                Toast.makeText(activity, "Please complete required data", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            view.findNavController()
                .navigate(
                    ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment2(
                        viewModel.newShoe.value
                    )
                )
        }
        return binding.root
    }
}