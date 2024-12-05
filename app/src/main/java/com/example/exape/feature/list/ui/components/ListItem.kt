package com.example.exape.feature.list.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.exape.R
import com.example.exape.feature.list.model.CharacterModel
import com.example.exape.feature.list.model.LocationModel
import com.example.exape.ui.theme.ExapeTheme

@Composable
fun ListItem(character: CharacterModel) {
    Log.d("IMAGE", character.image)
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .fillMaxHeight()
                    .width(122.dp),
                model = character.image,
                error = painterResource(R.drawable.image_error),
                placeholder = painterResource(R.drawable.image_placeholder),
                contentDescription = null,
                contentScale = ContentScale.FillBounds

                )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier) {
                Text(character.name)
                Status(character.status)
                Text("Especie: " + character.species)
                Text("Genero: " + character.gender)
                Text("Origem: " + character.origin.name)
            }
        }
    }
}

@Composable
fun Status(status: String) {
    val color = when (status.lowercase()) {
        "alive" -> Color.Green
        "dead" -> Color.Red
        else -> Color.Gray
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = color,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(6.dp, 2.dp)

    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(status)
    }
}

val mock = CharacterModel(
    id = 22,
    name = "Aqua Rick",
    status = "unknown",
    species = "Humanoid",
    type = "Fish-Person",
    gender = "Male",
    origin = LocationModel(name = "unknown", url = ""),
    location = LocationModel(
        name = "Citadel of Ricks",
        url = "https://rickandmortyapi.com/api/location/3"
    ),
    image = "https://rickandmortyapi.com/api/character/avatar/22.jpeg",
    episode = listOf(
        "https://rickandmortyapi.com/api/episode/10",
        "https://rickandmortyapi.com/api/episode/22",
        "https://rickandmortyapi.com/api/episode/28"
    ),
    url = "https://rickandmortyapi.com/api/character/22",
    created = "2017-11-04T22:41:07.171Z"
)

@Composable
@Preview
fun ListItemPreview() {
    ExapeTheme {
        ListItem(mock)
    }
}