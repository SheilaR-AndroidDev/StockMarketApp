package com.plcoding.stockmarketapp.presentation.company_listings

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.stockmarketapp.domain.model.CompanyListing

@Composable
fun CompanyItem(
    company: CompanyListing,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f) //to take as much space on the row as possible
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = company.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = company.exchange,
                    fontWeight = FontWeight.Light,
                    color= MaterialTheme.colors.onBackground)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier= Modifier.fillMaxWidth()) {
                Text(text = company.symbol,
                     fontWeight = FontWeight.Medium,
                     fontStyle= FontStyle.Italic,
                     color = MaterialTheme.colors.onBackground)
            }
        }
    }
}

/*
@Preview
@Composable
fun PreviewDetailScreen(){
    CompanyItem(company = CompanyListing("Tesla", "TES", "NASDAQ"))
}*/

