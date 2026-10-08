package id.ac.itera.profileapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.ac.itera.profileapp.ui.components.LabeledTextField
import id.ac.itera.profileapp.viewmodel.ProfileUiState

/** VIEW (stateless): form edit nama & bio, memakai LabeledTextField yang sama untuk kedua field. */
@Composable
fun EditProfileScreen(
    uiState: ProfileUiState,
    onNameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Edit Profile", style = MaterialTheme.typography.headlineMedium)

        LabeledTextField(
            label = "Nama",
            value = uiState.draftName,
            onValueChange = onNameChange,
            errorMessage = uiState.nameError
        )

        LabeledTextField(
            label = "Bio",
            value = uiState.draftBio,
            onValueChange = onBioChange,
            singleLine = false,
            minLines = 3,
            supportingText = "${uiState.draftBio.length}/${ProfileUiState.MAX_BIO_LENGTH}"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(onClick = onCancelClick, modifier = Modifier.weight(1f)) {
                Text("Batal")
            }
            Button(onClick = onSaveClick, modifier = Modifier.weight(1f)) {
                Text("Save")
            }
        }
    }
}
