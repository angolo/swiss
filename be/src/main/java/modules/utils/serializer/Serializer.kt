package base.main.modules.utils.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonEncoder
import org.bson.codecs.kotlinx.BsonDecoder

object Serializer:KSerializer<String> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            "ObjectIdAsStringSerializer",
            PrimitiveKind.STRING
        )

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): String {
        return when (decoder){
            is BsonDecoder->decoder.decodeBsonValue().asObjectId().value.toHexString()
            else-> throw Exception("Errore mentre deserializzavo")
        }
    }

    override fun serialize(encoder: Encoder, value: String) {
        when(encoder){
            is JsonEncoder-> encoder.encodeString(value)
            else-> throw Exception("Errore mentre serializzavo")
        }
    }
}