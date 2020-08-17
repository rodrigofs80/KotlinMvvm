package br.com.projkotlinexamples.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(var autor: String, var fonte: String, var legenda: String, var url: String) : Parcelable