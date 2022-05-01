package andrefigas.com.github.instagram

import andrefigas.com.github.instagram.R
import androidx.annotation.DrawableRes

data class FeedEntry(
    @DrawableRes val profileImgRes: Int,
    @DrawableRes val photoImgRes: Int,
    val profileName: String,
    val subtitle: String,
    val likes: Int
) {

    companion object {

        val feed = listOf(
            FeedEntry(
                R.drawable.profile1,
                R.drawable.profile1_feed1,
                "Barney",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                2
            ),
            FeedEntry(
                R.drawable.profile2,
                R.drawable.profile2_feed1,
                "Homer",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                3
            ),
            FeedEntry(
                R.drawable.profile2,
                R.drawable.profile2_feed2,
                "Homer",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                4
            ),
            FeedEntry(
                R.drawable.profile4,
                R.drawable.profile4_feed1,
                "Skinner",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                5
            ),
            FeedEntry(
                R.drawable.profile5,
                R.drawable.profile5_feed1,
                "Moe",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                6
            ),
            FeedEntry(
                R.drawable.profile6,
                R.drawable.profile6_feed1,
                "Apu",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                7
            ),
            FeedEntry(
                R.drawable.profile7,
                R.drawable.profile7_feed1,
                "Milhouse",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                8
            ),
            FeedEntry(
                R.drawable.profile7,
                R.drawable.profile7_feed2,
                "Milhouse",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                9
            ),
        )

    }

}
