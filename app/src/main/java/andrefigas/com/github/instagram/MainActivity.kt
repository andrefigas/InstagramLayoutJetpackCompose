package andrefigas.com.github.instagram

import andrefigas.com.github.instagram.ui.theme.InstagramTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramTheme {
                Scaffold(bottomBar = { BottomBar() }, drawerContent = { Text(text = "Drawer") }) {
                    Column {
                        AppBar()
                        MainContent()
                    }
                }

            }
        }
    }
}

@Composable
fun AppBar() {
    Card(backgroundColor = Color.White) {
        Row(
            Modifier
                .height(50.dp)
                .padding(5.dp)
        ) {
            Image(
                painterResource(R.drawable.logo_text),
                "logo",
                modifier = Modifier.fillMaxHeight()
            )

            Image(
                painterResource(R.drawable.ic_expand_more),
                modifier = Modifier.align(Alignment.CenterVertically),
                contentDescription = "More"

            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painterResource(R.drawable.ic_plus),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(46.dp)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp),
                    contentDescription = "Add"
                )
                Image(
                    painterResource(R.drawable.ic_heart),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(46.dp)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp),
                    contentDescription = "Favourite"
                )
                Image(
                    painterResource(R.drawable.ic_send),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(46.dp)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp),
                    contentDescription = "Send"
                )
            }

        }

    }
}


@Composable
fun MainContent() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Stories()
        }

        val feedEntries = FeedEntry.feed
        items(feedEntries.size) { index ->
            FeedItem(feed = feedEntries[index])
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Stories() {
    val stories = Avatar.stories
    LazyRow(modifier = Modifier.padding(top = 12.dp)) {
        items(stories.size) { index ->
            AvatarCircle(avatar = stories[index])
        }
    }

}

@Composable
fun AvatarCircle(avatar: Avatar) {


    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF9424B4),
            Color(0xFFB72485),
            Color(0xFFF52B24),
            Color(0xFFFF5B13),
            Color(0xFFFFAE1A)
        ),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(start = 5.dp, end = 5.dp)
    ) {

        Box() {
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .background(
                        brush = brush,
                    )
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(
                            Color.White
                        )
                        .align(Alignment.Center)
                ) {
                    Image(
                        painterResource(avatar.avatarImgRes),
                        "avatar",
                        modifier = Modifier
                            .width(68.dp)
                            .height(68.dp)
                            .clip(CircleShape)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop,
                    )
                }

            }

            if (avatar.user) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(
                            Color.White
                        )
                        .align(Alignment.BottomEnd)
                ) {
                    Image(
                        painterResource(R.drawable.ic_add_circle_fill),
                        "new story",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .clip(CircleShape)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop,
                    )
                }
            }

        }

        Text(
            text = if (avatar.user) "Your story" else avatar.name,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .align(CenterHorizontally),
            fontSize = 10.sp,
            fontWeight = FontWeight.Light
        )
    }

}

@Composable
fun FeedItem(feed: FeedEntry) {
    val configuration = LocalConfiguration.current

    Column(modifier = Modifier.padding(bottom = 5.dp)) {
        FeedHeader(feed = feed)
        Image(
            painterResource(feed.photoImgRes),
            feed.profileName + "'s photo",
            modifier = Modifier
                .fillMaxWidth()
                .height(configuration.screenWidthDp.dp),
            //.border(verticalGradientBrush,  CutCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
        )

        FeedOptionsPanel(feed = feed)

        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = feed.likes.toString().plus(" likes"),
                fontWeight = FontWeight.Bold, fontSize = 12.sp
            )
        }

        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle()) {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        ) {
                            append(feed.profileName)
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        ) {
                            append(" " + feed.subtitle)
                        }

                    }
                }
            )
        }


    }
}

@Composable
fun FeedHeader(feed: FeedEntry) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .padding(5.dp)
    ) {
        Image(
            painterResource(feed.profileImgRes),
            "'s avatar",
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = feed.profileName,
                modifier = Modifier.padding(5.dp),
                textAlign = TextAlign.Center
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painterResource(R.drawable.ic_more),
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .padding(5.dp),
                contentDescription = "More"
            )
        }

    }
}

@Composable
fun FeedOptionsPanel(feed: FeedEntry) {
    Row(modifier = Modifier.height(45.dp)) {

        Box(modifier = Modifier.size(45.dp), contentAlignment = Alignment.Center) {
            Image(
                painterResource(R.drawable.ic_heart),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(28.dp)
                    .height(28.dp),
                contentDescription = "Favourite",
            )
        }

        Box(modifier = Modifier.size(45.dp), contentAlignment = Alignment.Center) {
            Image(
                painterResource(R.drawable.ic_msg),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(28.dp)
                    .height(28.dp),
                contentDescription = "Message",
            )
        }

        Box(modifier = Modifier.size(45.dp), contentAlignment = Alignment.Center) {
            Image(
                painterResource(R.drawable.ic_send),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(28.dp)
                    .height(28.dp),
                contentDescription = "Send",
            )
        }

        Box(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
                .padding(end = 9.dp), contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painterResource(R.drawable.ic_bookmark),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(28.dp)
                    .height(28.dp),
                contentDescription = "Bookmark",
            )
        }
    }

}

@Composable
fun BottomBar() {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        contentColor = Color.White
    ) {
        BottomBarItem(this, true, R.drawable.ic_home, "home")
        BottomBarItem(this, false, R.drawable.ic_search, "search")
        BottomBarItem(this, false, R.drawable.ic_film, "film")
        BottomBarItem(this, false, R.drawable.ic_basket, "bag")
        AvatarBottomBarItem(this, false, Avatar.stories.first { it.user }.avatarImgRes, "profile")
    }
}

@Composable
fun BottomBarItem(
    rowScope: RowScope,
    selected: Boolean,
    @DrawableRes iconRes: Int,
    contentDescription: String
) {
    rowScope.BottomNavigationItem(
        selected = selected, onClick = { /*TODO*/ },
        icon = {

            Image(
                painterResource(iconRes),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(28.dp)
                    .height(28.dp),
                contentDescription = contentDescription,
            )
        },
        selectedContentColor = Color.Black,
        alwaysShowLabel = false,
    )
}

@Composable
fun AvatarBottomBarItem(
    rowScope: RowScope,
    selected: Boolean,
    @DrawableRes iconRes: Int,
    contentDescription: String
) {
    rowScope.BottomNavigationItem(
        selected = selected, onClick = { /*TODO*/ },
        icon = {

            Image(
                painterResource(iconRes),
                contentDescription,
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .clip(CircleShape)
            )
        },
        selectedContentColor = Color.Black,
        alwaysShowLabel = false,
    )
}



