package com.adhimbagas.finalprojectskripsi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KonselorModel(

        var name: String? = null,
        var institution: String? = null,
        var whatsapp: String? = null,
        var position: String? = null,
        var instagram: String? = null,
        var email: String? =null,
        var about: String? = null,
        var address: String? = null,
        var dailyPracticeL: String? = null

        ): Parcelable
