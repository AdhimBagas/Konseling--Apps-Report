package com.adhimbagas.finalprojectskripsi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    var title: String? = null,
    var imageArticle: String? = null,
    var dateCreated: String? = null,
    var content: String? = null,
    var link: String? = null
) : Parcelable

