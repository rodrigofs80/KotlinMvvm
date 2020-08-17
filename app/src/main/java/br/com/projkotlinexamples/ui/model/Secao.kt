package br.com.projkotlinexamples.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Secao(var nome: String, var url: String) : Parcelable