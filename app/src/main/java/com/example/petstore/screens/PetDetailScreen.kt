package com.example.petstore.screens

import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.SubcomposeAsyncImage
import com.example.petstore.R
import com.example.petstore.models.MediaType
import com.example.petstore.models.Pet
import com.example.petstore.models.PetMedia
import com.example.petstore.ui.theme.Secondary
import com.example.petstore.ui.theme.CaloriesBackground
import com.example.petstore.ui.theme.CaloriesContent
import com.example.petstore.ui.theme.CardBannerTextColor
import com.example.petstore.ui.theme.Primary
import androidx.core.net.toUri
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory

@Composable
fun PetDetailScreen(
    pet: Pet
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        // Hero section
        item {
            PetHeroSection(
                data = pet
            )
        }

        // Pet information
        item {
            PetInfoSection(
                data = pet
            )
        }

        item {
            descriptionSection(
                description = pet.description
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun PetVideoPlayer(
    resource: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val exoPlayer = remember(resource) {
        val renderersFactory = DefaultRenderersFactory(context)
            .setEnableDecoderFallback(true)

        ExoPlayer.Builder(context, renderersFactory)
            .build()
            .apply {
                val uri = Uri.parse(
                    "android.resource://${context.packageName}/raw/golden"
                )

                setMediaItem(MediaItem.fromUri(uri))
                prepare()
                playWhenReady = false
            }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = true
            }
        },
        modifier = modifier
    )
}

@Composable
fun PetMediaPager(
    media: List<PetMedia>,
    contentDescription: String
) {
    val pagerState = rememberPagerState(
        pageCount = { media.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->

        val item = media[page]

        when (item.type) {
            MediaType.IMAGE -> {
                SubcomposeAsyncImage(
                    model = item.url,
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    loading = {
                        PetImageFallback()
                    },
                    error = {
                        PetImageFallback()
                    }
                )
            }

            MediaType.VIDEO -> {
                item.resource?.let { resource ->
                    PetVideoPlayer(
                        resource = resource,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

    }
}

@Composable
private fun PetHeroSection(
    data: Pet
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

        if (data.media.isNotEmpty()) {
            PetMediaPager(
                media = data.media,
                contentDescription = data.name
            )
        } else {
            PetImageFallback()
        }

        // Dark gradient for readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.75f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(
                text = data.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = data.species,
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}

@Composable
private fun PetInfoSection(
    data: Pet
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(y = (-8).dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            PetInfoItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.pet_icon),
                        contentDescription = "Breed",
                        tint = Primary
                    )
                },
                label = "Breed",
                value = data.breed
            )

            PetInfoItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.size),
                        contentDescription = "Size",
                        tint = Primary
                    )
                },
                label = "Size",
                value = data.size
            )

            PetInfoItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Status",
                        tint = Primary
                    )
                },
                label = "Status",
                value = data.status
            )
        }
    }
}

@Composable
private fun PetInfoItem(
    icon: @Composable () -> Unit,
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon()

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = label,
            fontSize = 12.sp,
            color = CardBannerTextColor
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Secondary
        )
    }
}

@Composable
private fun descriptionSection(
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Text(
            text = "Description",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Secondary
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = description,
            fontSize = 15.sp,
            color = CardBannerTextColor
        )
    }
}