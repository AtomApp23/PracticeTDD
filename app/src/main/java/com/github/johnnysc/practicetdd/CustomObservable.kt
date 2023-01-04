package com.github.johnnysc.practicetdd

interface CustomObservable<T : CustomObject, E : CustomObserver<T>> : UpdateObject<T> {

    fun addObserver(observer: E)

    class Base<T : CustomObject, E : CustomObserver<T>> : CustomObservable<T, E> {

        private val observers = mutableListOf<E>()

        override fun addObserver(observer: E) {
            observers.add(observer)
        }

        override fun update(obj: T) {
            if (obj is CustomObject.Usual) {
                observers.forEach { observer ->
                    observer.update(obj)
                }
            } else {
                observers.forEach { observer ->
                    if (observer is CustomObserver.Premium<*>) {
                        observer.update(obj)
                    }
                }
            }
        }
    }
}

interface CustomObserver<T> : UpdateObject<T> {
    open class Usual<T>
    open class Premium<T>
}

interface CustomObject {
    open class Usual
    open class Premium
}

interface UpdateObject<T> {
    fun update(obj: T)
}