package com.github.johnnysc.practicetdd

interface CacheDataSource {

    fun add(item: SimpleData)
    fun data(): List<SimpleData>

    class Timed(
        private val now: Now,
        private val lifeTimeMillis: Long
    ) : CacheDataSource {

        private val simpleDataMap = mutableMapOf<Long, SimpleData>()
        private val simpleDataList = mutableListOf<SimpleData>()

        override fun add(item: SimpleData) {
            simpleDataMap[now.now()] = item
            simpleDataList.add(item)
        }

        override fun data(): List<SimpleData> {
            simpleDataMap.forEach {
                if (now.now() > it.key + lifeTimeMillis) {
                    simpleDataList.remove(it.value)
                }
            }
            return simpleDataList
        }

    }
}

interface Now {
    fun now(): Long
}