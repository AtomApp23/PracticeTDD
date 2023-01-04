package com.github.johnnysc.practicetdd

interface MyStack<T> {
    fun pop():T
    fun push(item:T)

    abstract class Base<T>(
        private val maxCount: Int,
        private val stackType: StackType
    ) : MyStack<T> {

        protected abstract val objects: MutableList<T>

        init {
            if (maxCount <= 0) {
                throw IllegalStateException("maxCount should be more than 0")
            }
        }

        override fun pop(): T {
            if (objects.isEmpty()) {
                throw IllegalStateException("You can't pop it")
            }
            val obj = when (stackType) {
                StackType.LIFO -> objects[objects.size - 1]
                StackType.FIFO -> objects[0]
            }
            objects.remove(obj)
            return obj

        }

        override fun push(item: T) {
            if (objects.size > maxCount) {
                throw IllegalStateException("Stack overflow exception, maximum is 1")
            }
            objects.add(item)
        }
    }

    class LIFO<T>(private val maxCount: Int) : Base<T>(maxCount, StackType.LIFO) {
        override val objects: MutableList<T> = mutableListOf()

    }

    class FIFO<T>(private val maxCount: Int) : Base<T>(maxCount, StackType.FIFO) {
        override val objects: MutableList<T> = mutableListOf()
    }

    enum class StackType {
        LIFO,
        FIFO
    }
}