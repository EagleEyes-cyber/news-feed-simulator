package id.ac.itera.profileapp.viewmodel

import androidx.lifecycle.ViewModel
import id.ac.itera.profileapp.data.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    repository: ProfileRepository = ProfileRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProfileUiState(profile = repository.getInitialProfile())
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // ---------- Edit Profile ----------
    fun onEditClick() = _uiState.update {
        it.copy(
            isEditing = true,
            draftName = it.profile.name,
            draftBio = it.profile.bio,
            nameError = null
        )
    }

    fun onNameChange(value: String) = _uiState.update {
        it.copy(draftName = value, nameError = null)
    }

    fun onBioChange(value: String) = _uiState.update {
        it.copy(draftBio = value.take(ProfileUiState.MAX_BIO_LENGTH))
    }

    fun onSaveClick() = _uiState.update { state ->
        if (state.draftName.isBlank()) {
            state.copy(nameError = "Nama tidak boleh kosong")
        } else {
            state.copy(
                profile = state.profile.copy(
                    name = state.draftName.trim(),
                    bio = state.draftBio.trim()
                ),
                isEditing = false,
                nameError = null
            )
        }
    }

    fun onCancelClick() = _uiState.update {
        it.copy(isEditing = false, nameError = null)
    }

    // ---------- Dark Mode ----------
    fun onDarkModeChange(enabled: Boolean) = _uiState.update {
        it.copy(isDarkMode = enabled)
    }
}
