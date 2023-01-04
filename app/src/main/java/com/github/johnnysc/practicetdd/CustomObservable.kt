package com.github.johnnysc.practicetdd

interface CustomObservable<T, E : CustomObserver<T>> : UpdateObjects<T> {

    fun addObserver(observer: E)
    fun removeObserver(observer: E)

    class Base<T, E : CustomObserver<T>>(private val maxCount: Int) : CustomObservable<T, E> {

        private val observers = mutableListOf<E>()
        private val inactiveObservers = mutableListOf<E>()

        override fun addObserver(observer: E) {
            if(observers.size < maxCount) {
                observers.add(observer)
            } else {
                inactiveObservers.add(observers[0])
                observers.removeFirst()
                observers.add(observer)
            }

        }

        override fun removeObserver(observer: E) {
            observers.remove(observer)
            if(observers.isEmpty() && inactiveObservers.isNotEmpty()) {
                inactiveObservers.forEach {
                    observers.add(it)
                }
            }
        }

        override fun update(argument: T) {
            observers.forEach { observer ->
                observer.update(argument)

            }
        }
    }
}

interface CustomObserver<T> : UpdateObjects<T>

interface UpdateObjects<T> {
    fun update(argument: T)
}