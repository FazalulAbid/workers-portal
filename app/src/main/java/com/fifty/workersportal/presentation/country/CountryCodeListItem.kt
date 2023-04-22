package com.fifty.workersportal.presentation.country

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.fifty.workersportal.R
import com.fifty.workersportal.domain.model.Country
import com.fifty.workersportal.presentation.common.HorizontalDivider
import com.fifty.workersportal.presentation.ui.theme.DarkTextColor
import com.fifty.workersportal.presentation.ui.theme.LightColor
import com.fifty.workersportal.presentation.ui.theme.LightTextColor
import com.fifty.workersportal.presentation.ui.theme.ScreenPaddingValue

@Composable
fun CountryCodeListItem(
    country: Country,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ScreenPaddingValue)
            .clickable {
                onItemClick()
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Country flag
                Image(
                    modifier = Modifier
                        .width(36.dp)
                        .height(24.dp)
                        .clip(shape = RoundedCornerShape(4.dp)),
                    painter = rememberImagePainter(
                        data = country.flagUrl,
                        builder = {
                            placeholder(R.drawable.fixitnow_logo)
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Country flag",
                    contentScale = ContentScale.Crop
                )
                // Spacer
                Spacer(modifier = Modifier.width(10.dp))
                // Country name
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.subtitle1,
                    color = DarkTextColor
                )
            }
            // Country code
            Text(
                text = "+${country.callingCode}",
                style = TextStyle(
                    fontFamily = MaterialTheme.typography.subtitle1.fontFamily,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Medium
                ),
                color = DarkTextColor
            )
        }
        // Divider.
        HorizontalDivider(color = LightColor)
    }
}