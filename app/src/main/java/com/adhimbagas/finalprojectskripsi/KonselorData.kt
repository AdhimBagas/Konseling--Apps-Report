package com.adhimbagas.finalprojectskripsi

object KonselorData {
    private val konselorNames = arrayOf(
            "Adhim Bagas",
            "Muntaha Heru",
            "Gatot Nurman",
            "jokowi wich"
    )
    private val konselorJabatan = arrayOf(
            "Universitas Negeri Semarang",
            "Universitas Sumatera Utara",
            "Universitas Pakualam"
    )

    private val konselorImage = intArrayOf(
            R.drawable.ic_person,
            R.drawable.ic_person,
            R.drawable.ic_person
    )

    val listData: ArrayList<Konselor>

    get(){
        val list = arrayListOf<Konselor>()
        for (position in konselorNames.indices){
            val konselor = Konselor()
            konselor.name = konselorNames[position]
            konselor.jabatan = konselorJabatan[position]
            konselor.photo = konselorImage[position]
            list.add(konselor)
        }
        return list
    }
}