package com.icretu.mypantry.core.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

inline fun <T> MutableStateFlow<T>.updateState(transform: T.() -> T) {
    update { current -> current.transform() }
}
