package id.ac.itera.profileapp.data

/** MODEL: data profil pengguna. Immutable, diubah lewat copy(). */
data class Profile(
    val name: String,
    val bio: String
)

/** MODEL: sumber data (sederhana, in-memory). Bisa diganti API/DB di pertemuan berikutnya. */
class ProfileRepository {
    fun getInitialProfile(): Profile = Profile(
        name = "Jova Pratama Nihanda",
        bio = "Mahasiswa Teknik Informatika ITERA yang sedang belajar Kotlin Multiplatform dan Compose."
    )
}
