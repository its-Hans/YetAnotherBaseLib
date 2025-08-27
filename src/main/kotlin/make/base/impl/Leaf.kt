package make.base.impl

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import make.base.api.Option
import make.base.api.roles.HasDefault
import make.base.api.roles.Setter
import make.base.api.tree.Leaf
import make.common.result.Ok
import make.common.result.Result
import java.util.concurrent.ConcurrentHashMap

class Leaf: Leaf {
    private var options: MutableMap<String, Option<*>> = HashMap()
}

class Option<V>(
    override val serializer: KSerializer<V>,
    private val get: () -> V
) : Option<V> {
    override val value: V get() = get()
}

class MutableOption<V>(
    override val serializer: KSerializer<V>,
    override val defaultValue: V,
    private val _set: (V, (V) -> Unit) -> Result<Unit, Any> = { value, set -> set(value); Ok(Unit) }
): Option<V>, Setter<V>, HasDefault<V> {

    private var _value = defaultValue
    override val value: V get() = _value

    override fun set(value: V): Result<Unit, Any> =
        _set(value) { _value = it }
}