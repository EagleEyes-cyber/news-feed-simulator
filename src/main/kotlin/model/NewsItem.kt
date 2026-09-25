package model

/**
 * Kategori berita yang tersedia di News Feed Simulator.
 */
enum class NewsCategory {
    TEKNOLOGI, OLAHRAGA, HIBURAN, POLITIK, EKONOMI
}

/**
 * Representasi ringkas sebuah berita, seperti yang muncul di dalam feed.
 */
data class NewsItem(
    val id: Int,
    val title: String,
    val category: NewsCategory,
    val timestamp: Long
)

/**
 * Detail lengkap sebuah berita, diambil secara terpisah (async) saat berita dibuka.
 */
data class NewsDetail(
    val id: Int,
    val fullContent: String,
    val author: String,
    val readTimeMinutes: Int
)
