package com.icretu.mypantry

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform