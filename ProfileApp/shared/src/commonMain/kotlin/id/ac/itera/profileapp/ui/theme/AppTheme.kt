package id.ac.itera.profileapp.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** Theme dengan transisi warna halus (animateColorAsState) saat dark mode di-toggle. */
@Composable
fun ProfileAppTheme(darkMode: Boolean, content: @Composable () -> Unit) {
    val target = if (darkMode) darkColorScheme() else lightColorScheme()
    val spec = tween<Color>(durationMillis = 400)

    @Composable
    fun anim(c: Color) = animateColorAsState(c, spec, label = "themeColor").value

    val colors = target.copy(
        primary = anim(target.primary),
        onPrimary = anim(target.onPrimary),
        primaryContainer = anim(target.primaryContainer),
        onPrimaryContainer = anim(target.onPrimaryContainer),
        background = anim(target.background),
        onBackground = anim(target.onBackground),
        surface = anim(target.surface),
        onSurface = anim(target.onSurface),
        surfaceVariant = anim(target.surfaceVariant),
        onSurfaceVariant = anim(target.onSurfaceVariant),
        outline = anim(target.outline)
    )
    MaterialTheme(colorScheme = colors, content = content)
}
