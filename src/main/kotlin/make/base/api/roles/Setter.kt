package make.base.api.roles

import make.common.result.Result

interface Setter<in T> {
    fun set(value: T): Result<Unit, Any>
}