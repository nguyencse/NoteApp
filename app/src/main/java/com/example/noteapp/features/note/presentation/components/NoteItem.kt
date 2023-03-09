package com.example.noteapp.features.note.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.noteapp.features.note.domain.entities.Note

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 4.dp,
    cutCornerRadius: Dp = 30.dp,
    onClickDelete: () -> Unit,
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val bgClipPath = Path().apply {
                lineTo(size.width - cutCornerRadius.value, 0f)
                lineTo(size.width, cutCornerRadius.value)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                lineTo(0f, 0f)
                close()
            }

            clipPath(path = bgClipPath) {
                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx()),
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(note.color, 0x000000, 0.2f)
                    ), topLeft = Offset(size.width - cutCornerRadius.toPx(), -100f), size = Size(
                        width = cutCornerRadius.toPx() + 100f,
                        height = cutCornerRadius.toPx() + 100f,
                    ), cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
            )
        }
        IconButton(
            onClick = onClickDelete,
            modifier = Modifier.align(Alignment.BottomEnd),
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete note")
        }
    }
}