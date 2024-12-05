package com.example.exape.feature.list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.exape.feature.list.ui.components.ListItem
import com.example.exape.feature.list.ui.components.RetrySection
import com.example.exape.ui.theme.ExapeTheme

@Composable
fun ListScreen(
    padding: PaddingValues,
    viewModel: ListViewModel = hiltViewModel()
) {
    val characterList by remember { viewModel.characterList }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(padding),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text("Personagens")
        }
        items(characterList.size) {
            if (it >= characterList.size - 1 && !isLoading) {
                viewModel.callApi()
            }
            ListItem(characterList[it])
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }
        if (loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.callApi()
            }
        }
    }
}


@Composable
@Preview
fun ListScreenPreview() {
    ExapeTheme {
        ListScreen(PaddingValues(16.dp))
    }
}