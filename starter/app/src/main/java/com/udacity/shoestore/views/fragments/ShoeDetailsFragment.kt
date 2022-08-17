package com.udacity.shoestore.views.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailsFragment : Fragment() {

    private val newShoe: Shoe = Shoe("", 0.0, "", "", arrayListOf())
    lateinit var binding: FragmentShoeDetailsBinding

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
        setHasOptionsMenu(true)
        binding.newShoe = newShoe

        binding.cancelButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(
                    ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment2(
                        null
                    )
                )
        }
        binding.saveButton.setOnClickListener { view ->
            if(!isDataValid())
            {
                Toast.makeText(activity, "Please complete required data", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            binding.apply {
                newShoe?.name = binding.shoeNameEditText.text.toString()
                newShoe?.size = binding.shoeSizeEditText.text.toString().toDouble()
                newShoe?.description = binding.descriptionEditText.text.toString()
                newShoe?.company = binding.companyEditText.text.toString()
            }
            view.findNavController()
                .navigate(
                    ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment2(
                        newShoe
                    )
                )
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    fun isDataValid(): Boolean {
        return (binding.shoeNameEditText.text?.isEmpty() == false) &&
                (binding.shoeSizeEditText.text?.isEmpty() == false) &&
                (binding.companyEditText.text?.isEmpty() == false) &&
                (binding.descriptionEditText.text?.isEmpty() == false)
    }
}