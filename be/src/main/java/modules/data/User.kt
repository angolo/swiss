package base.main.modules.data

import base.main.modules.utils.serializer.Serializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User (
    @SerialName("_id")
    @Serializable(with = Serializer::class)
    @Contextual val id: String? = null,
    val username : String,
    val activities : List<Activity>? = listOf()
)