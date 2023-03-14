

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sundroid.sundroid.R


@Composable
fun CircularImage(uri : String?) {
    Surface(
        modifier = Modifier.size(40.dp),
        shape = RoundedCornerShape(40.dp),
        color = Color.LightGray
    ) {
        if (uri != null && uri.length > 1 && uri != "null") {
            println("Destiny Uri not null $uri")
            AsyncImage(
                model = uri,
                contentDescription = null
            )
        } else {
            println("Destiny Uri null" )
            AsyncImage(
                model = R.drawable.ic_person,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

