package com.crocdc.solidwaffle

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.fetchImage(url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.placeholder)
        .into(this)
}
