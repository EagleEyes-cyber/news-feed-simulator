package id.ac.itera.myprofile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import myprofileapp.shared.generated.resources.Res
import myprofileapp.shared.generated.resources.photo_profile
import org.jetbrains.compose.resources.painterResource

data class ProfileData(
    val name: String,
    val title: String,
    val bio: String,
    val email: String,
    val phone: String,
    val location: String
)

private val sampleProfile = ProfileData(
    name = "Jova Pratama Nihanda",
    title = "Mahasiswa Teknik Informatika - ITERA",
    bio = "Seorang mahasiswa yang suka dalam bidang keamanan",
    email = "jova.124140019@student.itera.ac.id",
    phone = "+62 896-2431-9906",
    location = "Lampung Selatan, Indonesia"
)


@Composable
fun App() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ProfileScreen(profile = sampleProfile)
        }
    }
}

@Composable
fun ProfileScreen(profile: ProfileData) {
    var showContact by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfileHeader(name = profile.name, title = profile.title)

        ProfileCard(title = "Tentang Saya") {
            Text(
                text = profile.bio,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        AnimatedVisibility(visible = showContact) {
            ProfileCard(title = "Informasi Kontak") {
                InfoItem(
                    icon = Icons.Default.Email,
                    label = "Email",
                    value = profile.email
                )
                HorizontalDivider(color = Color.LightGray)
                InfoItem(
                    icon = Icons.Default.Phone,
                    label = "Phone",
                    value = profile.phone
                )
                HorizontalDivider(color = Color.LightGray)
                InfoItem(
                    icon = Icons.Default.LocationOn,
                    label = "Location",
                    value = profile.location
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /* TODO: aksi kirim pesan */ },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Kirim Pesan")
            }

            OutlinedButton(
                onClick = { showContact = !showContact },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (showContact) "Sembunyikan Info" else "Tampilkan Info")
            }
        }
    }
}



@Composable
fun ProfileHeader(
    name: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.tertiary
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(Res.drawable.photo_profile),
                contentDescription = "Foto profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.White, CircleShape)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = name,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun InfoItem(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Composable
fun ProfileCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}


@Composable
fun ProfileScreenPreview() {
    App()
}