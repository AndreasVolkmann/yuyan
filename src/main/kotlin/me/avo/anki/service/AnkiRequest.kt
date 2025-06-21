package me.avo.anki.service

import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo
import kotlinx.serialization.Serializable

class TypedAnkiRequest<TResponse : Any>(
    val request: AnkiRequest,
    val typeInfo: TypeInfo,
)

@Serializable
data class AnkiRequest(
    val action: String,
    val params: AnkiParams? = null,
    val version: Int = 6,

    ) {

    

    inline fun <reified TResponse : Any> withResponseType(): TypedAnkiRequest<TResponse> =
        TypedAnkiRequest(this, typeInfo<TResponse>())
}

@Serializable
data class AnkiParams(
    val note: Note? = null,
    val deckName: String? = null,
    val modelName: String? = null,
    val tags: List<String>? = null,
    val query: String? = null,
    val cards: List<Long>? = null,
    val card: Long? = null,
    val keys: List<String>? = null,
    val newValues: List<String>? = null,
    val path: String? = null,
    val filename: String? = null,
)


@Serializable
data class Note(
    val id: Long,
//    val modelName: String,
    val fields: Map<String, String>,
//    val tags: List<String>,
//    val options: Options,
)

@Serializable
data class Options(
    val allowDuplicate: Boolean = false,
)