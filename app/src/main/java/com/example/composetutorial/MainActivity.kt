package com.example.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column{
                        MessageBar()
                        Surface (modifier = Modifier.weight(1f, true)) {
                            Conversation(messages = SampleData.conversationSample)
                        }
                        MessageBar()
                    }

                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun ReceivedMessageCard(msg: Message) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 15.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
            )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 2.dp,
                modifier = Modifier.widthIn(0.dp, 250.dp),
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 10.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun SentMessageCard(msg: Message) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 15.dp)
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 2.dp,
                modifier = Modifier.widthIn(0.dp, 250.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 10.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Image(painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(messages) {message ->
            ReceivedMessageCard(msg = message)
            SentMessageCard(msg = message)
        }
    }
}

@Composable
fun MessageBar() {
    Surface(shadowElevation = 10.dp, modifier = Modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.Center) {
            Surface(
                shape = MaterialTheme.shapes.small,
                shadowElevation = 2.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                Text(
                    text = "Hello",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageBar() {
    ComposeTutorialTheme {
        Surface {
            MessageBar()
        }
    }
}

@Preview(name = "Light Mode")
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface {
            ReceivedMessageCard(
                msg = Message("Matt", "Take a look at Jetpack Compose, it's great!")
            )
        }
    }
}

@Preview
@Composable
fun PreviewSentMessageCard() {
    ComposeTutorialTheme {
        Surface {
            SentMessageCard(
                msg = Message("Matt", "Take a look at Jetpack Compose, it's great!")
            )
        }
    }
}

//@Preview( showBackground = true )
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}