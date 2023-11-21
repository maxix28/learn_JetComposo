package com.example.question

import WellnessViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel

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
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen { shouldShowOnboarding = false }
        } else {
            MySootheAppPortrait()
        }
    }
}








@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection() {
            AlignYourBodyRow()
        }
        HomeSection() {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))

        //SootheBottomNavigation(modifier)

    }
}

@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MySootheAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MySootheAppLandscape()
        }
    }
}

@Composable
fun MySootheAppLandscape() {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySootheAppPortrait() {

        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }

}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier,

    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text ="Home"
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Profile"
                )
            },
            selected = false,
            onClick = {}
        )
    }
}




@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
       verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(200) { item ->
            FavoriteCollectionCard()
        }
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
       // contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(200) { item ->
            AlignYourBodyElement()
        }
    }
}




@Composable
fun HomeSection(

    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text("Align your body",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
                    )
        content()
    }
}
@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(end = 8.dp)
    ) {
        Image(painter = painterResource(id = R.drawable._mg_9387_01), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape))
        Text(text ="Maxix", modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium)
    }
}






@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.padding(10.dp)
    ) {
        Row (   verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)){
            Image(
                painter = painterResource(R.drawable._mg_9353_01),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(text = "Nature Meditations",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}







@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    var textd by remember { mutableStateOf("") }
    TextField(
        value = textd,
        onValueChange = { textd  = it},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor  = MaterialTheme.colorScheme.surface,
            focusedBorderColor  = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text("Search")
        }



    )
}

















@Composable
fun WellnessScreen(modifier: Modifier = Modifier,
                   wellnessViewModel: WellnessViewModel = viewModel()) {
    Column(modifier = modifier.padding(top = 10.dp)) {
        StatefulCounter()
        val list = remember { getWellnessTasks().toMutableStateList() }

            // WellnessTasksList(list = list, onCloseTask = {task -> list.remove(task)})
        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task -> wellnessViewModel.remove(task) })
    }
}
@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = rememberSaveable { getWellnessTasks() },
    onCloseTask: (WellnessTask) -> Unit,
    onCheckedTask: (WellnessTask, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {

        items( items = list,
            key = { task -> task.id }) { task ->
            WellnessTaskItem(" ${task.label}" ,     onCheckedChange = { checked -> onCheckedTask(task, checked) }, onCloseTask = {onCloseTask(task)} )
        }
    }
}
private fun getWellnessTasks() = List(300) { i -> WellnessTask(i, "Task # $i") }

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}




@Composable

fun WellnessTaskItem(
    taskName: String,
    modifier: Modifier = Modifier,
    onCloseTask: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    var checkedState: Boolean by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = onCloseTask, // we will implement this later!
        modifier = modifier,
    )
}









@Composable
fun WellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}



@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    /*
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            var showTask by rememberSaveable { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text("You've had $count glasses.")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(
                onClick = { count = 0 },
                Modifier.padding(start = 8.dp)) {
                Text("Clear water count")
            }
        }
    }*/

    StatefulCounter(modifier)
}






@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    var waterCount by remember { mutableStateOf(0) }


    Column {

        StatelessCounter(waterCount, { waterCount++ })

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
   // var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Max`s Project")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
private fun Greetings1(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}



@Composable
private fun CardContent( name:String){
    var expanded by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Row(modifier = Modifier
            .weight(1f)
            .padding(12.dp)){
        Column(

    ) {
            Text(text = "Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
            Spacer(modifier = Modifier.fillMaxWidth(0.9f))
            Box(contentAlignment = Alignment.TopEnd) {
                IconButton(onClick = { expanded = !expanded },) {
                    Icon(
                        imageVector = if (expanded) {Icons.Filled.ExpandLess  } else Icons.Filled.ExpandMore

                        ,
                        contentDescription = if (expanded) {
                            "Show less"
                        } else {
                            "Show more"
                        }
                    )
                }
            }


    }



    }
}


@Composable
private fun Greeting(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 50.dp
        )
        ,
    ) {
        CardContent(name)
    }
}


@Composable
private fun Greeting1(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding  by animateDpAsState (if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow), label = ""
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "Hello, ")
                Text(text = name, style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
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
    MyApp()


}








