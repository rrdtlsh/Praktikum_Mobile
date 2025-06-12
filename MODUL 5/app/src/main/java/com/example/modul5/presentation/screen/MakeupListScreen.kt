package com.example.modul5.presentation.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.modul5.MakeupApp
import com.example.modul5.presentation.components.ProductItem
import com.example.modul5.presentation.navigation.Navigation
import com.example.modul5.presentation.viewmodel.MakeupViewModel
import com.example.modul5.presentation.viewmodel.MakeupViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeupListScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = (context.applicationContext as MakeupApp).repository
    val viewModel: MakeupViewModel = viewModel(factory = MakeupViewModelFactory(repository))

    val connectivityObserver = (context.applicationContext as MakeupApp).connectivityObserver
    val isOnline by connectivityObserver.observe().collectAsState(initial = false)

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Favorit")
    val localContext = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is MakeupViewModel.UiEvent.NavigateToDetail -> navController.navigate(Navigation.createDetailRoute(event.makeupId))
                is MakeupViewModel.UiEvent.OpenWebUrl -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.url))
                    try { localContext.startActivity(intent) }
                    catch (e: Exception) { Toast.makeText(localContext, "Tidak dapat membuka link", Toast.LENGTH_SHORT).show() }
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Makeup App", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title, fontWeight = FontWeight.Bold) }
                    )
                }
            }

            if (!isOnline) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.errorContainer)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.WifiOff,
                        contentDescription = "Offline",
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Anda sedang offline",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> ProductList(
                    products = uiState.products,
                    isLoading = uiState.isLoading,
                    error = uiState.error,
                    viewModel = viewModel,
                    isOnline = isOnline // Kirim status koneksi
                )
                1 -> ProductList(
                    products = uiState.favorites,
                    isLoading = false,
                    error = null,
                    viewModel = viewModel,
                    isOnline = isOnline, // Kirim status koneksi
                    emptyMessage = "Anda belum memiliki produk favorit."
                )
            }
        }
    }
}

@Composable
fun ProductList(
    products: List<com.example.modul5.data.model.MakeupProduct>,
    isLoading: Boolean,
    error: String?,
    viewModel: MakeupViewModel,
    isOnline: Boolean,
    emptyMessage: String = "Tidak ada produk yang ditemukan."
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading && products.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (error != null) {
            Text(text = error, modifier = Modifier.align(Alignment.Center).padding(16.dp), textAlign = TextAlign.Center)
        } else if (products.isEmpty()) {
            Text(text = emptyMessage, modifier = Modifier.align(Alignment.Center).padding(16.dp), textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products, key = { it.id }) { product ->
                    ProductItem(
                        product = product,
                        onVisitClick = { viewModel.onVisitClicked(product.imageLink) },
                        onDetailClick = { viewModel.onDetailClicked(product.id) },
                        onToggleFavorite = { viewModel.toggleFavorite(product.id) },
                        isOnline = isOnline
                    )
                }
            }
        }
    }
}