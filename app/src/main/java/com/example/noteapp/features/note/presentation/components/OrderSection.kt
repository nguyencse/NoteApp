package com.example.noteapp.features.note.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.features.note.domain.utils.NoteOrder
import com.example.noteapp.features.note.domain.utils.OrderType

@Composable
fun OrderSection(
    modifier: Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                title = "Date",
                checked = noteOrder is NoteOrder.Date,
                onSelect = { onOrderChange(NoteOrder.Date(orderType = noteOrder.orderType)) },
                modifier = modifier
            )
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                title = "Title",
                checked = noteOrder is NoteOrder.Title,
                onSelect = { onOrderChange(NoteOrder.Title(orderType = noteOrder.orderType)) },
                modifier = modifier
            )
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                title = "Color",
                checked = noteOrder is NoteOrder.Color,
                onSelect = { onOrderChange(NoteOrder.Color(orderType = noteOrder.orderType)) },
                modifier = modifier
            )
        }
        Spacer(modifier = modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                title = "Ascending",
                checked = noteOrder.orderType == OrderType.Ascending,
                onSelect = { onOrderChange(noteOrder.copy(OrderType.Ascending)) },
                modifier = modifier
            )
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                title = "Descending",
                checked = noteOrder.orderType == OrderType.Descending,
                onSelect = { onOrderChange(noteOrder.copy(OrderType.Descending)) },
                modifier = modifier
            )
        }
    }
}