package id.ac.itera.profileapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ac.itera.profileapp.ui.EditProfileScreen
import id.ac.itera.profileapp.ui.ProfileScreen
import id.ac.itera.profileapp.ui.theme.ProfileAppTheme
import id.ac.itera.profileapp.viewmodel.ProfileViewModel

@Composable
fun App(viewModel: ProfileViewModel = viewModel { ProfileViewModel() }) {
    // Satu-satunya tempat state di-collect dari ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileAppTheme(darkMode = uiState.isDarkMode) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (uiState.isEditing) {
                EditProfileScreen(
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onBioChange = viewModel::onBioChange,
                    onSaveClick = viewModel::onSaveClick,
                    onCancelClick = viewModel::onCancelClick,
                    modifier = Modifier.safeContentPadding()
                )
            } else {
                ProfileScreen(
                    uiState = uiState,
                    onEditClick = viewModel::onEditClick,
                    onDarkModeChange = viewModel::onDarkModeChange,
                    modifier = Modifier.safeContentPadding()
                )
            }
        }
    }
}
