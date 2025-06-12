package com.example.modul4.ui.components

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide

@Composable
fun GlideImageCrop(
    @DrawableRes resId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                contentDescription?.let { this.contentDescription = it }
            }
        },
        update = { imageView ->
            Glide.with(imageView.context)
                .load(resId)
                .into(imageView)
        },
        modifier = modifier
    )
}

@Composable
fun GlideImageFit(
    @DrawableRes resId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.FIT_CENTER
                contentDescription?.let { this.contentDescription = it }
            }
        },
        update = { imageView ->
            Glide.with(imageView.context)
                .load(resId)
                .into(imageView)
        },
        modifier = modifier
    )
}