package andrefigas.com.github.instagram

import androidx.annotation.DrawableRes

data class Avatar(@DrawableRes val avatarImgRes : Int, val name : String, val user : Boolean = false){

    companion object{
        val stories = listOf(Avatar(R.drawable.profile1, "Barney", true),
            Avatar(R.drawable.profile2, "Homer"),
            Avatar(R.drawable.profile3, "Smithers"),
            Avatar(R.drawable.profile4, "Skiner"),
            Avatar(R.drawable.profile5, "Moe"),
            Avatar(R.drawable.profile6, "Apu"),
            Avatar(R.drawable.profile7, "Milhouse"),
            Avatar(R.drawable.profile8, "Kirk"),
            Avatar(R.drawable.profile9, "Jailbird"),
        )
    }

}
