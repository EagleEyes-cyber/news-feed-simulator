import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import manager.ReadCounterManager
import model.NewsCategory
import repository.NewsRepository

/**
 * News Feed Simulator
 * Tugas Praktikum - Pertemuan 2 (Coroutines & Flow)
 *
 * Fitur yang diimplementasikan:
 * 1. Flow yang mensimulasikan berita baru setiap 2 detik      -> repository.newsFeed()
 * 2. Filter berita berdasarkan kategori tertentu               -> .filter { }
 * 3. Transform data menjadi format yang ditampilkan            -> .map { }
 * 4. StateFlow untuk jumlah berita yang sudah dibaca           -> ReadCounterManager
 * 5. Coroutines untuk mengambil detail berita secara async     -> launch(Dispatchers.IO) + suspend fun
 */
fun main() = runBlocking {
    val repository = NewsRepository()
    val readCounter = ReadCounterManager()

    val selectedCategory = NewsCategory.TEKNOLOGI

    println("=== News Feed Simulator ===")
    println("Menampilkan berita kategori: $selectedCategory")
    println("(simulasi berjalan 12 detik...)\n")

    // Collect StateFlow jumlah berita dibaca di coroutine terpisah
    val counterJob = launch {
        readCounter.readCount.collect { count ->
            println(">> [StateFlow] Total berita dibaca: $count")
        }
    }

    // Collect Flow berita, dengan filter, map, onEach, dan error handling (.catch)
    val feedJob = launch {
        repository.newsFeed()
            .filter { it.category == selectedCategory }        // 2. filter kategori
            .map { newsItem ->                                  // 3. transform ke format tampilan
                "[${newsItem.category}] ${newsItem.title}" to newsItem
            }
            .onEach { (formatted, _) ->
                println("\n📰 Berita baru masuk: $formatted")
            }
            .catch { e ->
                println("⚠️  Terjadi error pada feed: ${e.message}")
            }
            .collect { (_, newsItem) ->
                // 5. ambil detail berita secara async, tidak memblokir collector feed utama
                launch(Dispatchers.IO) {
                    try {
                        val detail = repository.fetchNewsDetail(newsItem)
                        println("   Detail: ${detail.fullContent} (${detail.readTimeMinutes} menit baca)")
                        readCounter.markAsRead()                // 4. update StateFlow
                    } catch (e: Exception) {
                        println("   Gagal memuat detail: ${e.message}")
                    }
                }
            }
    }

    delay(12_000L)
    feedJob.cancel()
    counterJob.cancel()

    println("\n=== Simulasi selesai ===")
    println("Total berita yang berhasil dibaca: ${readCounter.readCount.value}")
}
