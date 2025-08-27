package make.base.api.tree

import make.base.api.Option

sealed interface Node<T> {
    val elements: Map<String, T>
}

interface Group: Node<Node<*>>
interface Leaf: Node<Option<*>>