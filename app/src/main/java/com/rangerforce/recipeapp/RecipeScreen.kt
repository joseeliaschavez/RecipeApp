package com.rangerforce.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.rangerforce.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    val mainViewModel: MainViewModel = viewModel()
    val viewState = mainViewModel.category

    Box(modifier = modifier) {
        when {
            viewState.value.isLoading -> {
                LoadingView()
            }
            viewState.value.error != null -> {
                ErrorView(errorMessage = viewState.value.error)
            }
            viewState.value.recipes.isNotEmpty() -> {
                RecipeListView(categories = viewState.value.recipes)
            }
        }
    }
}

@Composable
fun RecipeListView(categories: List<Category>) {
    Box {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(categories) { category ->
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = category.thumbnailUrl),
            contentDescription = category.description,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.name,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun LoadingView() {
    Box {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ErrorView(errorMessage: String?) {
    Box {
        Text(text = errorMessage ?: "Error")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecipeAppTheme {
        RecipeScreen()
    }
}