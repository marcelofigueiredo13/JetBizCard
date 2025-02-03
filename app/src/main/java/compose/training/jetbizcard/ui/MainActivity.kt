package compose.training.com.habit.jetbizcard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.training.com.habit.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetBizCardTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(corner = CornerSize(50.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            UserImageCard()
            HorizontalDivider()
            CreateInfo()
        }
    }
}

@Composable
fun UserImageCard(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = alignment
    ) {
        Surface(
            modifier = modifier
                .size(150.dp)
                .padding(30.dp),
            shape = CircleShape,
            border = BorderStroke(0.5.dp, Color.LightGray),
            color = MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.5f
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = "User Image",
                modifier.background(
                    color = Color.White
                )
            )
        }
    }
}


@Composable
fun CreateInfo() {
    val context = LocalContext.current
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Miles P.",
            modifier = Modifier.padding(5.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Android Compose Programmer",
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "@themilescompose",
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.labelMedium
        )
        Button(
            modifier = Modifier.padding(20.dp),
            onClick = {
                buttonClickedState.value = !buttonClickedState.value
                Toast.makeText(context, "Showing portfolio", Toast.LENGTH_SHORT).show()
            }) {
            Text(
                text = "Portfolio",
                style = MaterialTheme.typography.labelMedium
            )
        }
        if (buttonClickedState.value) {
            Content()
        }
    }
}

@Preview
@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(2.dp, Color.DarkGray),
            color = Color.White
        ) {
            Portfolio(
                data = mutableListOf(
                    "Project 1",
                    "Project 2",
                    "Project 3",
                    "Project 4",
                    "Project 5"
                )
            )
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .background(
                            Color.White
                        ),
                ) {
                    UserImageCard(
                        modifier = Modifier.size(100.dp),
                        alignment = Alignment.Start
                    )
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .align(alignment = Alignment.CenterVertically),
                    ) {
                        Text(
                            text = item,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Awesome project",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        CreateBizCard()
    }
}
