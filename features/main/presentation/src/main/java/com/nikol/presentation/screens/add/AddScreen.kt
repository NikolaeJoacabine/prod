package com.nikol.presentation.screens.add

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.presentation.R
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavController,
    viewModel: AddViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val searchState by viewModel.actionState.collectAsState()
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { it != SheetValue.PartiallyExpanded }
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedImages by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        selectedImages = uri
    }

    //Добавка
    var nameFilm by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var janr by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F7))
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val currentState = searchState) {
                is RemoteObtainingLibrary.Loading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(trackColor = Color(0xFF7A5AF8))
                }

                is RemoteObtainingLibrary.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = currentState.message,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Нет нужного фильма?", color = Color.Gray)
                            TextButton(onClick = { showBottomSheet = true }) {
                                Text("добавить", color = Color(0xFF7A5AF8))
                            }
                        }
                    }
                }

                is RemoteObtainingLibrary.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            Spacer(Modifier.height(80.dp))
                        }
                        items(currentState.library) { movie ->
                            ItemMoveSearch(movie) {
                                viewModel.addMove(movie.id)
                            }
                        }
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Нет нужного фильма?", color = Color.Gray)
                                TextButton(onClick = { showBottomSheet = true }) {
                                    Text("добавить", color = Color(0xFF7A5AF8))
                                }
                            }
                        }
                    }
                }

                else -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Нет нужного фильма?", color = Color.Gray)
                        TextButton(onClick = { showBottomSheet = true }) {
                            Text("добавить", color = Color(0xFF7A5AF8))
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 12.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFF2F4F7), RoundedCornerShape(24.dp)),
                    placeholder = {
                        Text(
                            text = "Поиск фильмов...",
                            color = Color(0xFF7A5AF8).copy(alpha = 0.7f),
                            fontSize = 16.sp
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_back),
                                contentDescription = "Назад",
                                tint = Color(0xFF7A5AF8),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    trailingIcon = {
                        if (searchText.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Очистить",
                                tint = Color(0xFF7A5AF8),
                                modifier = Modifier
                                    .clickable { searchText = "" }
                                    .size(24.dp))
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333),
                        cursorColor = Color(0xFF7A5AF8),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        lineHeight = 20.sp
                    )
                )
                IconButton(
                    onClick = { viewModel.searchFilms(searchText) },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(0xFF7A5AF8),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Поиск",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }


        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = Color.White,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                // Заголовок
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Добавить свой фильм",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                    IconButton(
                        onClick = { showBottomSheet = false },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Закрыть",
                            tint = Color(0xFF666666)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Поле для названия
                OutlinedTextField(
                    value = nameFilm,
                    onValueChange = { nameFilm = it },
                    label = { Text("Название фильма") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF7A5AF8),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF7A5AF8),
                        cursorColor = Color(0xFF7A5AF8),
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Поле для описания
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Описание") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333),
                        focusedBorderColor = Color(0xFF7A5AF8),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF7A5AF8),
                        cursorColor = Color(0xFF7A5AF8)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 4
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Поле для жанра
                var expanded by remember { mutableStateOf(false) }
                val genres =
                    listOf("Драма", "Комедия", "Боевик", "Фантастика", "Ужасы", "Мелодрама")

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = janr,
                        onValueChange = {},
                        label = { Text("Жанр") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF333333),
                            unfocusedTextColor = Color(0xFF333333),
                            focusedBorderColor = Color(0xFF7A5AF8),
                            unfocusedBorderColor = Color(0xFFCCCCCC),
                            focusedLabelColor = Color(0xFF7A5AF8),
                            cursorColor = Color.Transparent, // Скрываем курсор
                            focusedTrailingIconColor = Color(0xFF7A5AF8),
                            unfocusedTrailingIconColor = Color(0xFF7A5AF8)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(if (expanded) R.drawable.arrow_drop_up else R.drawable.arrow_drop_down),
                                contentDescription = "Выбрать жанр",
                                tint = Color(0xFF7A5AF8)
                            )
                        },
                        readOnly = true // Делаем поле только для чтения
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .exposedDropdownSize(matchTextFieldWidth = true)
                            .background(Color.White)
                    ) {
                        genres.forEach { genre ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = genre,
                                        color = Color(0xFF333333),
                                        fontSize = 16.sp
                                    )
                                },
                                onClick = {
                                    janr = genre
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                            HorizontalDivider(
                                color = Color(0xFFEEEEEE),
                                thickness = 1.dp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    value = link,
                    onValueChange = { link = it },
                    label = { Text("Ссылка на фильм") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333),
                        focusedBorderColor = Color(0xFF7A5AF8),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF7A5AF8),
                        cursorColor = Color(0xFF7A5AF8)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.link),
                            contentDescription = "Ссылка",
                            tint = Color(0xFF7A5AF8)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (selectedImages != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    ) {
                        AsyncImage(
                            model = selectedImages,
                            contentDescription = "Постер фильма",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                },
                            contentScale = ContentScale.Crop
                        )

                        IconButton(
                            onClick = {
                                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Изменить фото",
                                tint = Color.White,
                                modifier = Modifier
                                    .background(Color(0x80000000), CircleShape)
                                    .padding(8.dp)
                            )
                        }
                    }
                } else {
                    OutlinedButton(
                        onClick = {
                            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(vertical = 16.dp),
                        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painterResource(R.drawable.add_photo_alternate),
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
                                tint = Color(0xFF7A5AF8)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Добавить постер",
                                color = Color(0xFF7A5AF8)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Кнопка сохранения
                Button(
                    onClick = { /* TODO: Сохранить */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7A5AF8)
                    )
                ) {
                    Text(
                        text = "Сохранить фильм",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ItemMoveSearch(item: Movie, onClick: () -> Unit) {

    var isFavorite by remember { mutableStateOf(false) }
    val rotation = animateFloatAsState(targetValue = if (isFavorite) 360f else 0f, label = "")
    val scale = animateFloatAsState(targetValue = if (isFavorite) 1.2f else 1f, label = "")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = "Постер фильма",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp) // Уменьшено с 16.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.title,
                        color = Color(0xFF7A5AF8),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    item.rating?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFF5F3FF),
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFD700),
                                modifier = Modifier.size(18.dp)
                            )

                            Text(
                                text = "%.1f".format(it),
                                color = Color(0xFF7A5AF8),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

                item.year?.let {
                    Text(
                        text = it.toString(),
                        color = Color(0xFF666666),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    ) // Уменьшено с 6.dp
                }


                Text(
                    text = item.description,
                    color = Color(0xFF333333),
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    Modifier.padding(top = 4.dp),
                ) {
                    Text(
                        text = "Жанр не указан",
                        color = Color(0xFF333333),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 4.dp)
                    ) // Уменьшено с 8.dp
                    IconButton(
                        onClick = {
                            isFavorite = !isFavorite
                            onClick()
                        },
                        modifier = Modifier
                            .size(36.dp)
                    ) {
                        AnimatedContent(
                            targetState = isFavorite,
                            transitionSpec = {
                                fadeIn() with fadeOut()
                            },
                            label = ""
                        ) { favorite ->
                            Icon(
                                imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Избранное",
                                tint = if (favorite) Color.Red else Color(0xFF744EDC),
                                modifier = Modifier
                                    .graphicsLayer {
                                        rotationZ = rotation.value
                                        scaleX = scale.value
                                        scaleY = scale.value
                                    }
                            )
                        }
                    }
                }

            }
        }
    }
}


fun bitmapToByteArray(context: Context, uri: Uri?): ByteArray {
    if (uri == null) {
        throw IllegalArgumentException("URI is null")
    }

    return uri.let { safeUri ->
        context.contentResolver.openInputStream(safeUri)?.use { inputStream ->
            val bitmap = BitmapFactory.decodeStream(inputStream)
                ?: throw IllegalStateException("Failed to decode bitmap from URI: $safeUri")
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos) // Увеличено качество до 70
            baos.toByteArray()
        } ?: throw IllegalStateException("Failed to open input stream for URI: $safeUri")
    }
}
