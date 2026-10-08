package id.ac.itera.profileapp.viewmodel

import id.ac.itera.profileapp.data.Profile

/** UI STATE: satu data class yang merepresentasikan seluruh kondisi layar. */
data class ProfileUiState(
    val profile: Profile,
    val isEditing: Boolean = false,
    // Draft = nilai sementara di form edit (baru disimpan ke profile saat Save)
    val draftName: String = "",
    val draftBio: String = "",
    val nameError: String? = null,
    val isDarkMode: Boolean = false
) {
    companion object {
        const val MAX_BIO_LENGTH = 150
    }
}
