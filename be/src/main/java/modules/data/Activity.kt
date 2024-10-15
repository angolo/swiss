package base.main.modules.data

import base.main.modules.utils.serializer.Serializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    @SerialName("_id")
    @Serializable(with = Serializer::class)
    @Contextual val id: String? = null,
    val name : String?=null,
    val startDate : Long?=null,
    val endDate : Long?=null,
    val type : String?=null,
)
