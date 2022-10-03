package com.eks.flowandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // this is a "flow", it's basically a coroutine so we can run suspend
    //functions inside of here. We wanna emmit numbers for a countdown.
    // the flow here is a cold flow: does not do anything unless
    // there are subscribers to it
    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            // we can use the suspend function "delay()"
            delay(1000L)
            currentValue--
            // returns whenever there's a change
            emit(currentValue)
        }
    }

    // here we can check in logs that the program notifies us
    // of value changes of time
    // collect vs collectLatest: collectLatest cancels the old emit
    // if a new emit comes1
    init {
        collectFlow()
    }
    private fun collectFlow() {
        viewModelScope.launch {
            countDownFlow.collect { time ->
                println("The current time is $time")
            }
        }
    }

}