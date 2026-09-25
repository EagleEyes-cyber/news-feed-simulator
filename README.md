# News Feed Simulator

Tugas Praktikum — IF25-22017 Pengembangan Aplikasi Mobile
Pertemuan 2: Advanced Kotlin, Coroutines, dan Flow

## Deskripsi

Aplikasi CLI sederhana yang mensimulasikan feed berita menggunakan **Kotlin Coroutines** dan **Kotlin Flow**, sesuai deskripsi tugas praktikum:

| # | Fitur yang diminta | Implementasi |
|---|---|---|
| 1 | Flow simulasi berita baru setiap 2 detik | `NewsRepository.newsFeed()` — `flow { ... delay(2000) ... emit(...) }` |
| 2 | Filter berita berdasarkan kategori | `.filter { it.category == selectedCategory }` di `Main.kt` |
| 3 | Transform data menjadi format tampilan | `.map { ... }` mengubah `NewsItem` menjadi string berformat |
| 4 | StateFlow jumlah berita yang sudah dibaca | `ReadCounterManager` (`MutableStateFlow<Int>`) |
| 5 | Coroutines untuk mengambil detail berita secara async | `suspend fun fetchNewsDetail()` dipanggil lewat `launch(Dispatchers.IO)` |

Sebagai tambahan (bonus rubrik +10%):
- **Error handling**: operator `.catch { }` pada Flow, ditambah `try/catch` di sekitar pemanggilan `fetchNewsDetail()` (fungsi ini sengaja sesekali melempar exception untuk mensimulasikan kegagalan network).
- **Unit test**: `src/test/kotlin/NewsFeedSimulatorTest.kt` menguji `ReadCounterManager` dan `NewsRepository` menggunakan `kotlinx-coroutines-test` (`runTest`).

## Struktur Proyek

```
NewsFeedSimulator/
├── build.gradle.kts
├── settings.gradle.kts
├── README.md
└── src
    ├── main/kotlin
    │   ├── Main.kt                     # entry point, merangkai semua fitur
    │   ├── model/NewsItem.kt           # NewsCategory, NewsItem, NewsDetail
    │   ├── repository/NewsRepository.kt# Flow berita + suspend fun detail berita
    │   └── manager/ReadCounterManager.kt # StateFlow jumlah berita dibaca
    └── test/kotlin
        └── NewsFeedSimulatorTest.kt    # unit test (bonus)
```

## Cara Menjalankan

Proyek ini menggunakan Gradle (Kotlin JVM).

1. Pastikan JDK 17+ terpasang.
2. Buka folder proyek di terminal, lalu jalankan:

   ```bash
   ./gradlew run
   ```

   (jika belum ada Gradle wrapper, jalankan `gradle wrapper` terlebih dahulu, atau buka proyek ini langsung di IntelliJ IDEA / Android Studio dan jalankan `Main.kt`).

3. Program akan berjalan selama ±12 detik, menampilkan berita baru (kategori **TEKNOLOGI**) setiap 2 detik beserta detailnya, dan mencetak jumlah berita yang sudah dibaca (StateFlow) setiap kali berubah.

### Menjalankan Unit Test

```bash
./gradlew test
```

## Contoh Output

```
=== News Feed Simulator ===
Menampilkan berita kategori: TEKNOLOGI
(simulasi berjalan 12 detik...)

📰 Berita baru masuk: [TEKNOLOGI] Peluncuran Smartphone Terbaru
   Detail: Ini adalah isi lengkap dari berita 'Peluncuran Smartphone Terbaru'. (3 menit baca)
>> [StateFlow] Total berita dibaca: 1

📰 Berita baru masuk: [TEKNOLOGI] AI Ciptakan Terobosan Baru
   Detail: Ini adalah isi lengkap dari berita 'AI Ciptakan Terobosan Baru'. (5 menit baca)
>> [StateFlow] Total berita dibaca: 2

=== Simulasi selesai ===
Total berita yang berhasil dibaca: 2
```

*(Berita di luar kategori TEKNOLOGI otomatis tersaring oleh `.filter`, dan sesekali muncul pesan error simulasi yang ditangani oleh `try/catch` maupun `.catch`.)*

## Konsep yang Digunakan

- **Flow builder** (`flow { emit(...) }`) — cold stream yang hanya berjalan saat di-collect.
- **Flow operators**: `filter`, `map`, `onEach`, `catch`.
- **StateFlow**: menyimpan state jumlah berita dibaca yang selalu punya nilai terbaru.
- **Coroutines**: `runBlocking`, `launch`, `suspend fun`, `Dispatchers.IO` untuk operasi yang mensimulasikan I/O.
- **Structured concurrency**: seluruh `launch` berjalan di dalam `runBlocking` scope sehingga otomatis dibatalkan (`cancel()`) di akhir simulasi.
