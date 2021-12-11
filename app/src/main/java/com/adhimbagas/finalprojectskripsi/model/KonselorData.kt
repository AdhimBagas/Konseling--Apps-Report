package com.adhimbagas.finalprojectskripsi.model

object KonselorData {
    fun generateKons (): ArrayList<KonselorModel>{

        val kons = ArrayList<KonselorModel>()

        kons.add(
                KonselorModel(
                        "Adhim Bagas",
                        "Universitas Semarang",
                        "+6285161710805",
                        "Konselor",
                        "saya adalah konselor di universitas negeri semaarang" +
                                "Sebagai salah satu konselor dengan jam terbang yang tinggi",
                        "Jl. Proyonanggan Utara selatana80 - 111203 Kauamn Batang jawa tengah",
                        "Senin - Jum'at\n" +
                                "09.00 - 10.00 WIB",
                        "https://i.ibb.co/88fPgBB/adhim-bagas.png"
                )
        )

        kons.add(
                KonselorModel(
                        "Nur Amalia",
                        "Universitas Gajah MADA",
                        "+6285161710805",
                        "Konselor",
                        "saya adalah konselor di universitas negeri semaarang" +
                                "Sebagai salah satu konselor dengan jam terbang yang tinggi",
                        "Jl. ahmad Yani No. 9 Kauman Batang",
                        "Senin - Jum'at\n" +
                                "09.00 - 10.00 WIB",
                        "https://i.postimg.cc/N0GfGCnS/humberto-chavez-FVh-yq-LR9e-A-unsplash-removebg-preview.png"
                )
        )

        kons.add(
                KonselorModel(
                        "Ramadhani N",
                        "Universitas Negeri Jakarta",
                        "+6285161710805",
                        "Konselor",
                        "saya adalah konselor di universitas negeri semaarang" +
                                "Sebagai salah satu konselor dengan jam terbang yang tinggi",
                        "Jl. ahmad Yani No. 9 Kauman Batang",
                        "Senin - Jum\'at\n09.00 - 10.00 WIB",
                        "https://i.postimg.cc/Nf1g6wqZ/usman-yousaf-p-Trhfmj2j-DA-unsplash-1-removebg-preview.png"
                )
        )


        return kons

    }
}