package com.example.modul3.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.modul3.model.Makeup
import com.example.modul3.ui.components.GlideImageFit

@Composable
fun MakeupDetailScreen(
    navController: NavController,
    makeupId: String
) {
    val item = Makeup.makeupList.find { it.id == makeupId } ?: return
    val scrollState = rememberScrollState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            GlideImageFit(
                resId = item.imageResId,
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nama: ${item.name}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Jenis: ${item.type}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Deskripsi:\n${item.description}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Kembali", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}