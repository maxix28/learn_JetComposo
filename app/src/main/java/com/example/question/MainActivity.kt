package com.example.question

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.question.ui.theme.QuestionTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mutableAnswerList = mutableListOf(
                Answer(null, "First answer", false),
                Answer(null, "Second answer", false),
                Answer(null, "Third answer", false)
                // Add more Answer objects as needed
            )
            QuestionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // SingleChoiseQuestion(mutableAnswerList)
                    Column {
                        //OnboardingScreen()
                        MyApp()
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier =Modifier){
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen { shouldShowOnboarding = false }
        } else {
            Greetings()
        }
    }
}
@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}
@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {
    // TODO: This state should be hoisted
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}




@Composable
private fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}
data class Message(val author: String, val body: String)


@SuppressLint("UnrememberedMutableState")
@Composable
fun DifferentButton(modifier: Modifier=Modifier){
    var expand by remember{ mutableStateOf(false)}
    Box(
        modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(40.dp)){
        ElevatedButton (
        onClick = { expand=!expand }
    ) {
        Text(text = if(expand)"Show more" else "Show lesssssssssssssssssss")
    }
}}


@Composable
 fun SingleChoiseQuestion(answers : List<Answer>){
     Column {
         if(answers.isEmpty()){
             Text("There are no answers to choose from!")
         }else{
             answers.forEach{it->
                 SurveyAnswer(answer = it)
             }
         }
     }

 }



@Composable
fun SurveyAnswer(answer: Answer){
    Row{
        val selected by remember {
            mutableStateOf(answer.selected)
        }
      //  Image(painter = answer.Image, contentDescription =" ")
        Text(answer.text)
        RadioButton(selected = selected, onClick = { /*TODO*/ })


    }
}




@Composable
fun MessageCard(msg: Message) {

    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(bottom = 5.dp)
            .background(Color.Cyan)){
            Text("Personal Blog dean69", fontWeight = FontWeight.Bold, modifier = Modifier.padding( 5.dp))

        }
        Spacer(modifier = Modifier.height(4.dp))
        Row (modifier = Modifier.padding(all = 8.dp)){
            Image(
                painter = painterResource(R.drawable._mg_9387_01),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(50.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape),

                )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = msg.author,color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.body,
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
        Image(
            painter = painterResource(R.drawable._mg_9387_01),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                //.size(50.dp)
                // Clip image to be shaped as a circle

                .padding(start = 20.dp, end  =20.dp)

            )
        val checked  by remember {
            mutableStateOf(false)
        }

        IconToggleButton(
            checked = checked,
            onCheckedChange = { }) {
            Icon(
                modifier = Modifier
                    .size(78.dp)
                    .padding(start = 20.dp),
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null
            )
        }







    }

}

@Preview()
@Composable
fun PreviewMessageCard() {
    MessageCard(
        msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
    )


}








