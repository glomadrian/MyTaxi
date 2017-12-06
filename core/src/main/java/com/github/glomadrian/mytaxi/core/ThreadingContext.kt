package com.github.glomadrian.mytaxi.core

import kotlin.coroutines.experimental.CoroutineContext
import kotlin.properties.Delegates

var MAIN: CoroutineContext by Delegates.notNull()
var BACKGROUND: CoroutineContext by Delegates.notNull()