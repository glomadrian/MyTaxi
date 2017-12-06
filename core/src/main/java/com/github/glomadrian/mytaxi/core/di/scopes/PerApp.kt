package com.github.glomadrian.mytaxi.core.di.scopes

import java.lang.annotation.Retention
import javax.inject.Scope

import java.lang.annotation.RetentionPolicy.RUNTIME

@Scope
@Retention(RUNTIME)
annotation class PerApp
