package ru.lihogub.common

import org.jetbrains.kotlinx.multik.ndarray.data.D1Array
import org.jetbrains.kotlinx.multik.ndarray.operations.map
import org.jetbrains.kotlinx.multik.ndarray.operations.sum
import kotlin.math.pow

fun measureVector(vector: D1Array<Double>): Double = vector
    .map { x -> x * x }
    .sum()
    .pow(0.5)