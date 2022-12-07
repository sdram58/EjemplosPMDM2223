package com.catata.exampleobservable

class Observable<T>(private var value:T) {
    private val listObservers:MutableList<(T)->Unit> = mutableListOf()

    fun subscribe(observer: (T)->Unit){
        listObservers.add(observer)
        observer(value)
    }

    fun unsubscribe(observer: (T)->Unit){
        listObservers.remove(observer)
    }

    fun update(newValue: T){
        value = newValue
        notifyValues()
    }

    private fun notifyValues(){
        listObservers.forEach(){ observer ->
            observer(value)
        }
    }
}