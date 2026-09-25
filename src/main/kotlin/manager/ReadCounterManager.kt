package manager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Mengelola state jumlah berita yang sudah dibaca menggunakan StateFlow,
 * agar UI (atau siapa pun yang collect) selalu mendapat nilai terbaru.
 */
class ReadCounterManager {

    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    fun markAsRead() {
        _readCount.value++
    }

    fun reset() {
        _readCount.value = 0
    }
}
