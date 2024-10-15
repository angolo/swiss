package com.example.swiss.ui.items

import android.view.RoundedCorner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swiss.network.data.common.UserModel
import com.example.swiss.ui.theme.SwissTheme


@Composable
fun UserItem(
    user: UserModel?,
    onClick: () -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
                RoundedCornerShape(6.dp)
            )
            .clickable(onClick = onClick)
            .padding(10.dp)
    ) {
        Row {
            Text(user?.username ?: "")
            Spacer(modifier = Modifier.weight(1f))
            Text("N. attività: ${user?.activities?.size ?: 0}")
        }
    }
}

@Composable
@Preview
private fun UserItemPreview() {
    SwissTheme(darkTheme = false) {
        UserItem(
            user = UserModel(
                username = "Pierpaolo",
                activities = listOf()
            )
        )
    }
}