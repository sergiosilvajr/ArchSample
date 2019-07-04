package com.sergiosilvajr.archsample

import android.graphics.Color
import android.util.Log
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    val counter = MutableLiveData<String>()
    var color = MutableLiveData<String>()
    var view: CounterView? = null

    init {
        counter.postValue("0")
        color.postValue("#FFFFFF")
    }

    fun addOne() {
        val value = counter.value?.toIntOrNull()

        if (value == null) {
            view?.showErrorPopup()
            return
        }

        value?.plus(1)?.let {
            counter.postValue(it.toString())
            if (it > 100) {
                view?.warningMoreThan100()
            }
        }

        color.postValue("#FF0000")
    }


    fun removeOne() {
        val value = counter.value?.toIntOrNull()

        if (value == null) {
            view?.showErrorPopup()
            return
        }

        value?.minus(1)?.let {
            counter.postValue(it.toString())
        }
        color.postValue("#0000FF")
    }

    companion object {
        @JvmStatic
        @BindingAdapter("colorOnBackground")
        fun changeColor(view: ViewGroup, color: MutableLiveData<String>) {
            color.value?.let {
                view.setBackgroundColor(Color.parseColor(it))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        view = null
    }
}

