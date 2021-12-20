import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.composables.ui.theme.White

@Composable
fun CustomDropdownMenu(
    list: List<String>, // Menu Options
    defaultSelected: String, // Default Selected Option on load
    color: Color, // Color
    modifier: Modifier, //
    onSelected: (Int) -> Unit, // Pass the Selected Option
) {
    var selectedIndex by remember { mutableStateOf(0) }
    var expand by remember { mutableStateOf(false) }
    var stroke by remember { mutableStateOf(1) }
    Box(
        modifier
            .padding(8.dp)
            .border(
                border = BorderStroke(stroke.dp, color),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                expand = true
                stroke = if (expand) 2 else 1
            },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = defaultSelected,
            color = color,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        DropdownMenu(
            expanded = expand,
            onDismissRequest = {
                expand = false
                stroke = if (expand) 2 else 1
            },
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
            modifier = Modifier
                .background(White)
                .padding(2.dp)
                .fillMaxWidth(.4f)
        ) {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        expand = false
                        stroke = if (expand) 2 else 1
                        onSelected(selectedIndex)
                    }
                ) {
                    Text(
                        text = item,
                        color = color,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

    }
}
