package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace(
                        onBackClick = {},
                        onNextClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpace(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val currentImageIndex = remember { mutableStateOf(0) }
    val imageList = listOf(
        painterResource(id = R.drawable.vancouver),
        painterResource(id = R.drawable.quebec),
        painterResource(id = R.drawable.toronto)
    )
    val titleList = listOf(
        stringResource(id = R.string.city_1),
        stringResource(id = R.string.city_2),
        stringResource(id = R.string.city_3),
    )
    val artistList = listOf(
        stringResource(id = R.string.artist_1),
        stringResource(id = R.string.artist_2),
        stringResource(id = R.string.artist_3),
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center

    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            alignment = Alignment.Center,


            contentDescription = "Artwork",
            contentScale = ContentScale.FillBounds,
            painter = imageList[currentImageIndex.value]
        )

        ArtworkDescriptor(
            currentImageIndex = currentImageIndex,
            titleList = titleList,
            artistList = artistList
        )
        DisplayController(
            currentImageIndex = currentImageIndex,
            onNextClick = onNextClick,
            onBackClick = onBackClick,
            imageListSize = imageList.size
        )
    }
}

@Composable
fun ArtworkDescriptor(
    currentImageIndex: MutableState<Int>,
    titleList: List<String>,
    artistList: List<String>
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(titleList[currentImageIndex.value], style = MaterialTheme.typography.displayMedium)
        Text(artistList[currentImageIndex.value], style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DisplayController(
    currentImageIndex: MutableState<Int>,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    imageListSize: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            currentImageIndex.value = (currentImageIndex.value + imageListSize - 1) % imageListSize
            onBackClick()
        }) {
            Text(text = "Back")
        }
        Button(onClick = {
            currentImageIndex.value = (currentImageIndex.value + 1) % imageListSize
            onNextClick()
        }) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpace(
            onBackClick = {},
            onNextClick = {}
        )
    }
}
