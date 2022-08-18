package com.udacity.shoestore.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private var _shoesList: MutableLiveData<MutableList<Shoe>> =
        MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    private var _newShoe: MutableLiveData<Shoe> = MutableLiveData<Shoe>()
    val newShoe: LiveData<Shoe>
        get() = _newShoe

    init {
        _newShoe.value = Shoe("",0.0,"","")
        shoesList()
    }

    private fun shoesList() {
        _shoesList.value = mutableListOf(
            Shoe(
                "React Infinity",
                11.2,
                "NIKE",
                "Nike React Infinity Run Flyknit",
                arrayListOf("https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/i1-665455a5-45de-40fb-945f-c1852b82400d/react-infinity-run-flyknit-mens-running-shoe-zX42Nc.jpg")
            ),
            Shoe("React Miler", 11.2, "NIKE", "Nike React Miler", arrayListOf("")),
            Shoe(
                "Air Zoom",
                11.2,
                "NIKE",
                "Nike React Infinity Run Flyknit",
                arrayListOf("https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/i1-5cc7de3b-2afc-49c2-a1e4-0508997d09e6/react-miler-mens-running-shoe-DgF6nr.jpg")
            ),
            Shoe(
                "Joyride Run",
                11.2,
                "NIKE",
                "Nike Joyride Run Flyknit",
                arrayListOf("https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/99a7d3cb-e40c-4474-91c2-0f2e6d231fd2/joyride-run-flyknit-womens-running-shoe-HcfnJd.jpg")
            ),
        )
    }

    fun addNewShoe(shoe: Shoe) {
        shoesList.value?.add(shoe)
    }

    fun isDataValid(): Boolean {
        if(_newShoe.value == null) {
            return false;

        }
        return _newShoe.value?.name!!.isNotEmpty() &&
                (_newShoe.value?.size!! > 0.0) &&
                (_newShoe.value?.company!!.isNotEmpty())
                && (_newShoe.value?.description!!.isNotEmpty())
    }
}