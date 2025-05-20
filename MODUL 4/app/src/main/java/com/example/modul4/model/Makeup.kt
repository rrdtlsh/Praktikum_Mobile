package com.example.modul4.model

import com.example.modul4.R

data class Makeup(
    val id: String,
    val name: String,
    val type: String,
    val imageResId: Int,
    val webUrl: String,
    val description: String,
    val year: String
) {
    companion object {
        val makeupList = listOf(
            Makeup(
                id = "1",
                name = "MakeOver Powerstay 24H Weightless Liquid Foundation",
                type = "Foundation",
                imageResId = R.drawable.makeover_foundation,
                webUrl = "https://www.sociolla.com/foundation/74784-powerstay-24h-weightless-liquid-foundation",
                description = "Produk ini memberikan tampilan kulit yang lebih halus pada aplikasi pertama dan mempertahankan penampilannya sepanjang hari.\n\nTidak hanya memberikan kesan ringan pada kulit saat digunakan di dalam, luar ruangan, dan aktivitas aktif, tetapi juga berinovasi dengan Oil Regulatory Technology yang menggabungkan mekanisme ganda mikropartikel, memajukan formula untuk tidak hanya mampu mengendalikan minyak berlebih yang muncul di kulit, tetapi juga mengatur produksi minyak dari bawah kulit. Memungkinkan untuk tetap diam dengan sempurna hingga 24 jam tanpa munculnya sebum/minyak berlebih.",
                year = "2023"
            ),
            Makeup(
                id = "2",
                name = "MakeOver Powerstay Glazed Lock Lip Pigment",
                type = "Lipstik",
                imageResId = R.drawable.makeover_lipstik,
                webUrl = "https://www.sociolla.com/lip-gloss/81512-powerstay-glazed-lock-lip-pigment?shade=d09_skye_glaze_%7C_3_g",
                description = "Make Over Powerstay Glazed Lock Lip Pigment merupakan level terbaru dari lip gloss, memberikan hasil bibir plump dan glazy yang uncrackable (tampilan tahan lama tanpa cracking) hingga 24 jam.\n\nPlump Glaze menghasilkan tampilan bibir halus bahkan ketika digunakan di bibir kering, glossy, dan pigmented hanya dalam 1 olesan, bahkan ketika digunakan di bibir gelap. Diformulasikan dengan paten POWERGLAZE TECHNOLOGY™, intensitas pigmen produk ini dapat tahan sepanjang hari tanpa retak, tahan transfer, dan tahan noda.",
                year = "2024"
            ),
            Makeup(
                id = "3",
                name = "Maskara Tahan Air MAKE OVER Lash Impulse",
                type = "Mata",
                imageResId = R.drawable.makeover_maskara,
                webUrl = "https://www.sociolla.com/mascara/56820-lash-impulse-waterproof-mascara",
                description = "Make Over Lash Impulse Waterproof Mascara 9 ml adalah maskara tahan air dengan 3D Maxi-Lash Technology yang menghasilkan volume 10x* pada bulu mata anda menjadikannya lebih tebal, lentik, dan lebat.\n\nMaskara ini diformulasikan dengan formula zero-smudge yang menjaga kinerja maskara ini tidak luntur hingga 12 jam. Diinovasikan dengan customized dual sided flat-curve brush yang secara presisi didesain untuk memberikan hasil maksimal tanpa menggumpal.",
                year = "2022"
            ),
            Makeup(
                id = "4",
                name = "MakeOver Multifix Matte Blusher",
                type = "Pipi",
                imageResId = R.drawable.makeover_blush,
                webUrl = "https://www.sociolla.com/blush/10174-multifix-matte-blusher",
                description = "Semarakkan penampilan Anda dengan blusher stick transformasi unik ini. Dengan Powder Shifter Technology, tekstur krimnya langsung berubah menjadi bedak lembut yang meleleh di kulit.\n\nTeksturnya yang lembut dan creamy membuat blush ini menyatu sempurna di kulit, namun hasil akhir bedak lembut memberikan tampilan matte yang menggoda dan aplikasi yang ringan. Dilengkapi formula Hi-Impact Pigmented dan Color Diffused Tone Up yang memberikan rona alami yang intens namun halus yang menghadirkan kesan modern pada wajah yang lebih cerah.",
                year = "2023"
            ),
            Makeup(
                id = "5",
                name = "MakeOver Hyperblack Superstay Liner 1 g",
                type = "Mata",
                imageResId = R.drawable.makeover_liner,
                webUrl = "https://www.sociolla.com/eyeliner/9116-hyperblack-superstay-liner",
                description = "Pena eyeliner dengan warna intens yang dirancang untuk memberikan hasil akhir yang tegas dan dramatis.\n\nDilengkapi dengan aplikator berujung kuas yang lembut, tipis, dan sangat fleksibel, produk ini memungkinkan pengaplikasian yang presisi tinggi — mulai dari garis halus natural hingga winged liner yang berani. Formula cepat kering dan tahan lama memastikan riasan mata tetap tajam dan tidak luntur sepanjang hari, bahkan di kondisi lembap atau berminyak. Cocok untuk digunakan sehari-hari maupun tampilan glamor di acara khusus, eyeliner ini memberikan sentuhan akhir yang profesional tanpa repot.",
                year = "2023"
            ),
            Makeup(
                id = "6",
                name = "MakeOver Powerstay 24H Matte Powder Foundation",
                type = "Powder",
                imageResId = R.drawable.makeover_powder,
                webUrl = "https://www.sociolla.com/cake-foundation/80666-powerstay-24h-matte-powder-foundation",
                description = "Make Over Powerstay 24H Matte Powder Foundation merupakan bedak padat dengan kandungan foundation untuk mendapatkan hasil Airbrushed Smooth Cover hingga 24 jam.\n\nPartikel mikro yang sangat halus dapat menempel secara sempurna dan menutupi segala permasalahan kulit wajah, bahkan pada kulit mudah berjerawat, kulit kering dan penggunaan diatas tabir surya. Dapatkan hasil yang tahan lama dan nyaman bahkan di kulit yang sangat berminyak dengan 24H Strong-wear Triple Oil Control.",
                year = "2024"
            ),
            Makeup(
                id = "7",
                name = "MakeOver Hydration Serum",
                type = "Kulit",
                imageResId = R.drawable.makeover_serum,
                webUrl = "https://www.sociolla.com/face-serum/2314-hydration-serum-33-ml",
                description = "Primer wajah ini melembapkan dan mempersiapkan kulit sebelum makeup, menjadi rahasia tampilan riasan yang tahan lama dan flawless.\n\nDengan tekstur ringan yang mudah meresap, produk ini membantu menghaluskan kulit dan membuat makeup menempel lebih baik. Diperkaya dengan Aloe Vera untuk menenangkan dan melembapkan, Pro-Vitamin B5 untuk menjaga kelembapan kulit, serta Vitamin E sebagai antioksidan pelindung, primer ini menjadikan kulit terasa lebih sehat, kenyal, dan siap untuk riasan yang awet sepanjang hari.",
                year = "2022"
            )
        )
    }
}