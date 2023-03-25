package com.sundroid.sundroid.data.local.dao.database_models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Entity(tableName = "shop")
data class Shop
@JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true) var shopId: Int = 0,
    var name: String = "",
    var address: String = "",
    var owner: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
    var location: String = "",
    var phone: String = ""
) {
    companion object {
        fun getSampleJobs(): ArrayList<Shop> {
            val shops = arrayListOf<Shop>()
            var shop = Shop(
                name = "Lambert Laundry",
                address = "House 140, Dutse Baupma, Bwari Abuja",
                owner = "adegbesundayadoyi@gmail.com",
                location = "dutse",
                )
            shops.add(shop)
            shop = Shop(
                name = "Michael Funiture",
                address = "House 140, Dutse Baupma, Bwari Abuja",
                owner = "sundroid2020@gmail.com",
                location = "dutse",
                )
            shops.add(shop)
            return shops
        }
    }
}
