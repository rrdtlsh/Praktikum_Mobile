package com.example.modul5.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5.data.model.MakeupProduct
import com.example.modul5.data.repository.MakeupRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class MakeupListUiState(
    val isLoading: Boolean = true,
    val products: List<MakeupProduct> = emptyList(),
    val favorites: List<MakeupProduct> = emptyList(),
    val error: String? = null
)

class MakeupViewModel(private val repository: MakeupRepository) : ViewModel() {

    private val TAG = "MakeupViewModel"

    private val _uiState = MutableStateFlow(MakeupListUiState())
    val uiState: StateFlow<MakeupListUiState> = _uiState.asStateFlow()

    private val _detailProduct = MutableStateFlow<MakeupProduct?>(null)
    val detailProduct: StateFlow<MakeupProduct?> = _detailProduct.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    init {
        observeProductsFromCache()
        observeFavorites()
        refreshProducts()
    }

    private fun refreshProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.fetchAndCacheProducts()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = "Gagal memuat data: ${e.message}") }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun observeProductsFromCache() {
        repository.getProductsFromCache()
            .onEach { products ->
                Log.d(TAG, "Data Home diperbarui dari cache: ${products.size} item.")
                _uiState.update { it.copy(isLoading = false, products = products) }
            }
            .launchIn(viewModelScope)
    }

    private fun observeFavorites() {
        repository.getFavoriteProducts()
            .onEach { favoriteProducts ->
                Log.d(TAG, "Data Favorit diperbarui dari cache: ${favoriteProducts.size} item.")
                _uiState.update { it.copy(favorites = favoriteProducts) }
            }
            .launchIn(viewModelScope)
    }

    fun getProductDetails(id: Int) {
        viewModelScope.launch {
            Log.d(TAG, "Mengambil detail untuk produk ID: $id")
            repository.getProductById(id).collect { product ->
                _detailProduct.value = product
            }
        }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            Log.d(TAG, "Tombol Favorit ditekan untuk ID: $id")
            val productToToggle = _detailProduct.value?.takeIf { it.id == id }
                ?: _uiState.value.products.find { it.id == id }
                ?: _uiState.value.favorites.find { it.id == id }

            productToToggle?.let {
                repository.toggleFavoriteStatus(id, !it.isFavorite)
            }
        }
    }

    fun onDetailClicked(id: Int) {
        Log.d(TAG, "Tombol Detail ditekan untuk ID: $id")
        val selectedProduct = _uiState.value.products.find { it.id == id } ?: _uiState.value.favorites.find { it.id == id }
        Log.d(TAG, "Data yang dipilih: ${selectedProduct?.name}")
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.NavigateToDetail(id))
        }
    }

    fun onVisitClicked(url: String?) {
        Log.d(TAG, "Tombol Kunjungi ditekan. URL: $url")
        if (!url.isNullOrBlank()) {
            viewModelScope.launch {
                _eventFlow.emit(UiEvent.OpenWebUrl(url))
            }
        }
    }

    sealed class UiEvent {
        data class NavigateToDetail(val makeupId: Int) : UiEvent()
        data class OpenWebUrl(val url: String) : UiEvent()
    }
}