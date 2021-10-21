package com.example.getandpostrequestsbonus
class Custompeople : ArrayList<Custompeople.CustompeopleItem>(){
    data class CustompeopleItem(
        val name: String,
        val pk: Int
    )
}