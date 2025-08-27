package make.base.api

import kotlinx.serialization.KSerializer

interface Option<V> {
    val serializer: KSerializer<V>
    val value: V
}