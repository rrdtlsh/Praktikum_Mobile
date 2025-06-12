package com.example.modul5.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.modul5.MakeupApp
import com.example.modul5.R
import com.example.modul5.presentation.viewmodel.MakeupViewModel
import com.example.modul5.presentation.viewmodel.MakeupViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeupDetailScreen(
    navController: NavController,
    makeupId: Int
) {
    val context = LocalContext.current
    val repository = (context.applicationContext as MakeupApp).repository
    val viewModel: MakeupViewModel = viewModel(factory = MakeupViewModelFactory(repository))

    LaunchedEffect(key1 = makeupId) {
        viewModel.getProductDetails(makeupId)
    }

    val product by viewModel.detailProduct.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Kembali", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                actions = {
                    product?.let { p ->
                        IconButton(onClick = { viewModel.toggleFavorite(p.id) }) {
                            Icon(
                                imageVector = if (p.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Toggle Favorite",
                                tint = if (p.isFavorite) MaterialTheme.colorScheme.primary else Color.Gray,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (product == null) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                val p = product!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(p.directImageLink)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                        error = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = p.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(horizontal = 24.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                        Text(
                            text = p.name ?: "Nama Produk Tidak Tersedia",
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = p.productType ?: "Tipe Produk",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "Deskripsi",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = p.description ?: "Tidak ada deskripsi yang tersedia untuk produk ini.",
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 24.sp,
                            textAlign = TextAlign.Justify
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Kembali", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}