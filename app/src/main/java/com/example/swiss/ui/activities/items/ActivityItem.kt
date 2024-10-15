package com.example.swiss.ui.activities.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.swiss.network.data.common.Activity
import com.example.swiss.utils.enums.ActivityTypeEnum
import java.text.SimpleDateFormat
import java.util.Date


@Composable
internal fun ActivityItem(
    activity: Activity
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
                RoundedCornerShape(6.dp)
            )
            .padding(10.dp)
    ) {
        val activityEnum = remember {
            ActivityTypeEnum.entries.firstOrNull { it.name == activity.type }
                ?: ActivityTypeEnum.RUN
        }

        Column {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(painter = painterResource(activityEnum.icon), null)
                Text(activityEnum.activityName)

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    activity.endDate?.let {
                        SimpleDateFormat(
                            "dd/MM/yyyy",
                            java.util.Locale.getDefault()
                        ).format(Date(it))
                    } ?: ""
                )
            }
            Text(activity.name ?: "")
        }

    }

}