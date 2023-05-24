package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                ArtSpaceScreen()
            }
        }
    }
}

data class ArtState(
    val paintingResource: Int,
    val titleResource: Int,
    val authorResource: Int
)

val states = listOf(
    ArtState(
        paintingResource = R.drawable.monalisa,
        titleResource = R.string.mona_lisa,
        authorResource = R.string.leonardo_da_vinci
    ),
    ArtState(
        paintingResource = R.drawable.vertumnus,
        titleResource = R.string.vertumnus,
        authorResource = R.string.giuseppe_arcimboldo
    ),
    ArtState(
        paintingResource = R.drawable.self_portrait_with_felt_hat,
        titleResource = R.string.self_portrait_with_felt_hat,
        authorResource = R.string.vincent_van_gogh
    ),
    ArtState(
        paintingResource = R.drawable.portrait_of_adele_bloch_bauer_i,
        titleResource = R.string.portrait_of_adele_bloch_bauer_i,
        authorResource = R.string.gustav_klimt
    ),
    ArtState(
        paintingResource = R.drawable.the_girl_with_the_pearl_earring,
        titleResource = R.string.girl_with_a_pearl_earring,
        authorResource = R.string.johannes_vermeer
    ),
)

@Composable
fun ArtSpaceScreen() {
    ImageAndInfo()
}

@Composable
fun ImageAndInfo(
    modifier: Modifier = Modifier
) {
    var art by remember { mutableStateOf(0) }
    val state = remember { mutableStateOf(states[art]) }
    val currentArtState = state.value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = currentArtState.paintingResource),
            contentDescription = null,
            modifier = modifier
                .weight(3f)
                .widthIn(300.dp, 800.dp)
                .shadow(10.dp)
                .border(3.dp, colorResource(id = R.color.grey))
                .border(40.dp, colorResource(id = R.color.white)),
        )
        Spacer(modifier = modifier.height(24.dp))
        Column(
            modifier = modifier
                .widthIn(300.dp, 600.dp)
                .height(80.dp)
                .shadow(5.dp )
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.white))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = currentArtState.titleResource),
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraLight
            )
            Text(
                text = stringResource(id = currentArtState.authorResource),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = modifier.height(24.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (art in 1..4) {
                        art --
                        state.value = states[art]
                    } else {
                        art = 4
                        state.value = states[art]
                    }
                },
                modifier = modifier.width(170.dp)
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = modifier.height(8.dp))
            Button(
                onClick = {
                    if (art in 0..3) {
                        art++
                        state.value = states[art]
                    } else {
                        art = 0
                        state.value = states[art]
                    }
                },
                modifier = modifier.width(170.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}