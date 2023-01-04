package com.github.johnnysc.practicetdd

interface CustomObservable<T, E : CustomObserver<T>> : UpdateObjects<T> {

    fun addObserver(observer: E)
    fun removeObserver(observer: E)

    class Base<T, E : CustomObserver<T>> : CustomObservable<T, E> {

       private val observers = mutableListOf<E>()

        override fun addObserver(observer: E) {
            observers.add(observer)
        }

        override fun update(argument: T) {
            observers.forEach { observer ->
                observer.update(argument)

            }
        }

        override fun removeObserver(observer: E) {
            observers.remove(observer)
        }

    }
}

interface CustomObserver<T> : UpdateObjects<T>

interface UpdateObjects<T> {
    fun update(argument: T)
}