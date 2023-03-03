package ru.rifnur.tasktracker.mappers.v2.exceptions

import kotlin.reflect.KClass

class UnknownRequestClass(clazz: KClass<*>) : RuntimeException("Class $clazz cannot be mapped to Context")
