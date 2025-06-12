package com.example.modul4.ui.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.modul4.Navigation
import com.example.modul4.model.Makeup
import com.example.modul4.ui.components.GlideImageCrop
import com.example.modul4.viewmodel.MakeupViewModel
import com.example.modul4.viewmodel.MakeupViewModel.UiEvent
import com.example.modul4.viewmodel.MakeupViewModelFactory
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "MakeupListScreen"

@Composable
fun MakeupListScreen(
    navController: NavController,
    viewModel: MakeupViewModel = viewModel(factory = MakeupViewModelFactory())
) {
    val makeupList by viewModel.makeupList.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.NavigateToDetail -> {
                    Log.d(TAG, "Navigating to detail screen: ${event.makeupId}")
                    navController.navigate(Navigation.createDetailRoute(event.makeupId))
                }

                is UiEvent.OpenWebUrl -> {
                    Log.d(TAG, "Opening URL: ${event.url}")
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.url))
                    context.startActivity(intent)
                }
            }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(makeupList) { item ->
            MakeupItem(
                makeup = item,
                onVisitClick = { viewModel.onVisitClicked(item.webUrl) },
                onDetailClick = { viewModel.onDetailClicked(item.id) }
            )
        }
    }
}

@Composable
fun MakeupItem(
    makeup: Makeup,
    onVisitClick: () -> Unit,
    onDetailClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            GlideImageCrop(
                resId = makeup.imageResId,
                contentDescription = makeup.name,
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = makeup.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${makeup.type} | ${makeup.year}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Deskripsi: ${makeup.description}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            Log.d(TAG, "Tombol Visit ditekan: ${makeup.webUrl}")
                            onVisitClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(6.dp)
                    ) {
                        Text("Kunjungi", style = MaterialTheme.typography.bodyMedium)
                    }

                    Button(
                        onClick = {
                            Log.d(TAG, "Tombol Detail ditekan: ${makeup.id}")
                            onDetailClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(6.dp)
                    ) {
                        Text("Detail", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
