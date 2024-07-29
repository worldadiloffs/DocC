package uz.itschool.docc.model

import android.net.Uri
import com.google.gson.Gson
import uz.itschool.docc.utils.JsonNavType
import java.io.Serializable

data class Card(
    val title: String,
    val imageId: Int,
    val illness1: String ,
    val illness2: String
): Serializable {
    override fun toString(): String = Uri.encode(Gson().toJson(this))

}

class CardArgType : JsonNavType<Card>(){
    override fun fromJsonParse(value: String): Card = Gson().fromJson(value, Card::class.java)

    override fun Card.getJsonParse(): String = Gson().toJson(this)
}