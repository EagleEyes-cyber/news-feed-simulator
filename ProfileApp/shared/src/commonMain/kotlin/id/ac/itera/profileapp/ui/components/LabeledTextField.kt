package id.ac.itera.profileapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * STATELESS TextField (state hoisting).
 * Tidak punya state internal: nilai datang dari parent (value),
 * perubahan dikirim naik lewat callback (onValueChange).
 */
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    minLines: Int = 1,
    errorMessage: String? = null,
    supportingText: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        minLines = if (singleLine) 1 else minLines,
        isError = errorMessage != null,
        supportingText = {
            val text = errorMessage ?: supportingText
            if (text != null) Text(text)
        },
        modifier = modifier.fillMaxWidth()
    )
}
