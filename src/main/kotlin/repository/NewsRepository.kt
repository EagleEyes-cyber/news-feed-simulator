package repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import model.NewsCategory
import model.NewsDetail
import model.NewsItem

/**
 * Sumber data berita. Dalam aplikasi nyata, kelas ini akan memanggil API atau database.
 * Di sini semua data disimulasikan untuk keperluan latihan Coroutines & Flow.
 */
class NewsRepository {

    private val sampleTitles = listOf(
        "Peluncuran Smartphone Terbaru" to NewsCategory.TEKNOLOGI,
        "Timnas Menang Telak di Laga Tandang" to NewsCategory.OLAHRAGA,
        "Film Baru Raih Box Office" to NewsCategory.HIBURAN,
        "DPR Sahkan RUU Baru" to NewsCategory.POLITIK,
        "Rupiah Menguat terhadap Dolar" to NewsCategory.EKONOMI,
        "AI Ciptakan Terobosan Baru" to NewsCategory.TEKNOLOGI,
        "Atlet Bulu Tangkis Raih Emas" to NewsCategory.OLAHRAGA,
        "Konser Musik Pecahkan Rekor Penonton" to NewsCategory.HIBURAN
    )

    /**
     * Flow "cold" yang mensimulasikan berita baru muncul setiap 2 detik.
     * Flow baru berjalan ketika ada collector, dan setiap collector memulai
     * simulasi dari awal — sesuai sifat cold stream pada Kotlin Flow.
     */
    fun newsFeed(): Flow<NewsItem> = flow {
        var id = 1
        while (true) {
            delay(2000L) // simulasi berita baru setiap 2 detik
            val (title, category) = sampleTitles.random()
            emit(
                NewsItem(
                    id = id++,
                    title = title,
                    category = category,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    /**
     * Suspend function untuk mengambil detail berita secara asynchronous
     * (mensimulasikan network/database call yang butuh waktu).
     * Sesekali melempar exception untuk menguji error handling (.catch).
     */
    suspend fun fetchNewsDetail(newsItem: NewsItem): NewsDetail {
        delay(500L) // simulasi network call
        if (Random.nextInt(0, 20) == 0) {
            throw RuntimeException("Gagal memuat detail berita id=${newsItem.id}")
        }
        return NewsDetail(
            id = newsItem.id,
            fullContent = "Ini adalah isi lengkap dari berita '${newsItem.title}'.",
            author = "Redaksi ITERA News",
            readTimeMinutes = Random.nextInt(1, 6)
        )
    }
}
