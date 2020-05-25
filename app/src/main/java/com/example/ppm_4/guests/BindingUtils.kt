package com.example.ppm_4.guests

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.ppm_4.R

@BindingAdapter("typeImage")
fun ImageView.setTypeImage(index: Int?) {
    Log.i("Binding", index.toString())
    index?.let {
        setImageResource(when (index) {
            1 -> R.mipmap.role1
            2 -> R.mipmap.role2
            3 -> R.mipmap.role3
            4 -> R.mipmap.role4
            5 -> R.mipmap.role5
            6 -> R.mipmap.role6
            7 -> R.mipmap.role7
            8 -> R.mipmap.role8
            9 -> R.mipmap.role9
            else -> R.mipmap.role10


        })
    }
}