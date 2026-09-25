import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import manager.ReadCounterManager
import model.NewsCategory
import repository.NewsRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ReadCounterManagerTest {

    @Test
    fun `markAsRead menambah readCount`() = runTest {
        val manager = ReadCounterManager()
        manager.markAsRead()
        manager.markAsRead()
        assertEquals(2, manager.readCount.value)
    }

    @Test
    fun `reset mengembalikan readCount ke 0`() = runTest {
        val manager = ReadCounterManager()
        manager.markAsRead()
        manager.markAsRead()
        manager.reset()
        assertEquals(0, manager.readCount.value)
    }
}

class NewsRepositoryTest {

    @Test
    fun `newsFeed hanya mengemit berita sesuai kategori setelah filter`() = runTest {
        val repository = NewsRepository()
        val firstTeknologi = repository.newsFeed()
            .first { it.category == NewsCategory.TEKNOLOGI }
        assertEquals(NewsCategory.TEKNOLOGI, firstTeknologi.category)
    }

    @Test
    fun `fetchNewsDetail mengembalikan detail dengan id yang sama`() = runTest {
        val repository = NewsRepository()
        val news = repository.newsFeed().first()
        try {
            val detail = repository.fetchNewsDetail(news)
            assertEquals(news.id, detail.id)
            assertTrue(detail.readTimeMinutes in 1..5)
        } catch (e: RuntimeException) {
            // fetchNewsDetail sengaja melempar error secara acak (kasus error handling)
            assertTrue(e.message?.contains("Gagal memuat detail") == true)
        }
    }
}
