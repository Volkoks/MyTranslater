package com.example.mytranslater.di.annatation

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class VIewModelKey(val value: KClass<out ViewModel>)
