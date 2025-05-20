package com.example.modul4.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.modul4.ui.components.GlideImageFit
import com.example.modul4.viewmodel.MakeupViewModel
import com.example.modul4.viewmodel.MakeupViewModelFactory

private const val TAG = "MakeupDetailScreen"

@Composable
fun MakeupDetailScreen(
    navController: NavController,
    makeupId: String,
    viewModel: MakeupViewModel = viewModel(factory = MakeupViewModelFactory())
) {
    val makeupList by viewModel.makeupList.collectAsStateWithLifecycle()
    val item = makeupList.find { it.id == makeupId }

    Log.d(TAG, "Opening detail for ID: $makeupId")

    if (item == null) {
        Log.w(TAG, "Item with ID $makeupId not found")
        return
    } else {
        Log.i(TAG, "Item found: ${item.name}")
        Log.d(
            TAG, """
            Selected item details:
            ID: ${item.id}
            Name: ${item.name}
            Type: ${item.type}
            Year: ${item.year}
            URL: ${item.webUrl}
            Description (short): ${item.description.take(30)}...
        """.trimIndent()
        )
    }

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

            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${item.type} | ${item.year}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Deskripsi",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    Log.d(TAG, "Back button clicked")
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kembali", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}