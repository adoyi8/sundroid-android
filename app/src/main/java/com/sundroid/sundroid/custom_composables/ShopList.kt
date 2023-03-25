package com.sundroid.sundroid.custom_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.sundroid.sundroid.R
import com.sundroid.sundroid.data.local.dao.database_models.Shop
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.viewmodel.SundroidViewModel


















@Composable
fun ShopList(
    @PreviewParameter(MyListProvider::class) shops: List<Shop>,
    viewModel: SundroidViewModel
) {
    LazyColumn {
        items(shops) { shop ->
            ShopCard(shop, viewModel)
        }
    }
}


@Composable
fun ShopCard(shop: Shop, viewModel: SundroidViewModel) {
    var onClick: () -> Unit = {
        viewModel.bottomSheetAction.value = BottomSheetAction.VIEW_SHOP
        viewModel.shopFormState.convertToFormState(shop)
        viewModel.showBottomSheet()
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                shop.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                shop.address?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

            }

            Column() {
                val modifier = Modifier
                    .padding(0.dp, 16.dp, 16.dp, 0.dp)
                    .width(100.dp)

                Image(
                    painter = painterResource(id = R.drawable.ic_jobs2),
                    contentDescription = "Hello",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = modifier,
                    alignment = Alignment.TopEnd

                )

            }
        }

    }
}


