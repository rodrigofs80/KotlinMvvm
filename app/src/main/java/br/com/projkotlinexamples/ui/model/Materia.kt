package br.com.projkotlinexamples.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Materia(
    var autores: List<String>,
    var informePublicitario: Boolean,
    var subTitulo: String,
    var texto: String,
    var atualizadoEm: String,
    var id: Int,
    var publicadoEm: String,
    var secao: Secao,
    var tipo: String,
    var titulo: String,
    var url: String,
    var urlOriginal: String,
    var imagens: List<Image>
) : Parcelable {

    fun formatData(atualizadoEm: String) : String {
        val d: Array<String> = atualizadoEm.split("T").toTypedArray().get(0).split("-").toTypedArray()
        val h: Array<String> = atualizadoEm.split("T").toTypedArray().get(1).split(":").toTypedArray()
        return d[2] + "/" + d[1] + "/" + d[0] + " " + h[0] + ":" + h[1]
    }
}