package dehghan.daniyal.onlineshopapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dehghan.daniyal.onlineshopapp.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvanceButton(
    title:String,
    subTitle:String,
    imageVector: ImageVector,
    iconBackgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        onClick = { onClick() }, modifier = Modifier
            .background(Color.Transparent)
            .padding(20.dp, 0.dp)
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Card(
                modifier = Modifier
                    .padding(5.dp, 8.dp, 5.dp, 5.dp)
                    .background(iconBackgroundColor, RoundedCornerShape(50.dp)),
                shape = RoundedCornerShape(50.dp)
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .background(iconBackgroundColor)
                        .padding(10.dp),
                    tint = Color.White,
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = subTitle,
                    fontSize = 16.sp,
                    color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
                )
            }
        }
    }
}