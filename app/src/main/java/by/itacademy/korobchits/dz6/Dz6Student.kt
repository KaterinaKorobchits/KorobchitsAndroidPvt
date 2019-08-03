package by.itacademy.korobchits.dz6

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Dz6Student(
    @Expose(serialize = false, deserialize = true)
    @SerializedName("objectId")
    val id: String,

    @Expose
    @SerializedName("imageUrl")
    val imageUrl: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("age")
    val age: Int
)