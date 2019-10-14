package com.example.easybreakfast.ui.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easybreakfast.Constants.Companion.appId
import com.example.easybreakfast.Constants.Companion.appKey
import com.example.easybreakfast.model.Hit
import com.example.easybreakfast.model.ResponseModel
import com.example.easybreakfast.network.RecipeApi
import com.example.easybreakfast.ui.main.dialogs.OnFailerDialog
import com.example.easybreakfast.ui.main.dialogs.ProgressDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    var recipeList = MutableLiveData<List<Hit>>()
    var context : FragmentActivity?=null
    var progDlg = ProgressDialogFragment()

    init {
        getRecipes("chicken")
    }

    fun getRecipes(recipe: String) {
        progDlg.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (context!=null)progDlg.show(context?.supportFragmentManager!!, "Load")
        if (recipe.isNotEmpty()) RecipeApi.retrofitService.getProperties(recipe, appId,appKey).enqueue(object: Callback<ResponseModel> {

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (context!=null)OnFailerDialog().show(context?.supportFragmentManager!!, "Fail")

                if (progDlg.isAdded)progDlg.dismiss()
            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.body()!=null) recipeList.postValue(response.body()?.hits)
                if (progDlg.isAdded)progDlg.dismiss()
            }
        })
    }


}