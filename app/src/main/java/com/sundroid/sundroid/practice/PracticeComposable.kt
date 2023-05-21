import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.unit.*

// Define a custom shape that users can draw with their mouse.
val shape = Modifier.pointerInput(
    onPointerMove = {
        // Update the shape's position based on the user's input.
        shape.x = it.x
        shape.y = it.y
    },
    onPointerUp = {
        // Save the shape's current position.
        shape.lastX = it.x
        shape.lastY = it.y
    }
)

val MyLayout = @Composable fun() {
    // Create a ComposeBox that contains the custom shape.
    Box(
        modifier = shape,
        content = {
            // Draw the shape.
            Canvas(
                modifier = Modifier.fillMaxWidth(),
                onDraw = {
                    // Draw a rectangle with the shape's current dimensions.
                    val paint = Paint().apply {
                        color = Color.Red
                        strokeWidth = 2f
                    }
                    val rect = Rect(
                        shape.x.toFloat(),
                        shape.y.toFloat(),
                        shape.width.toFloat(),
                        shape.height.toFloat()
                    )
                    drawRect(rect, paint)
                }
            )
        }
    )
}

// Create a new App that displays the MyLayout layout.
