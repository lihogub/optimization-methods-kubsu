package ru.lihogub.task02

import kotlin.math.sqrt

fun findFunctionExtremumGoldenRatio(epsilon: Double, leftBound: Double, rightBound: Double, func: (Double) -> Double, debug: Boolean = false): Double {
    val goldenRatio = 1.5 - sqrt(5.0) / 2.0
    var a = leftBound
    var b = rightBound

    var y: Double = a + goldenRatio * (b - a)
    var z: Double = (b + a) - y
    var fy = func(y)
    var fz = func(z)
    var k = -1


    while ((b - a) > epsilon) {
        if (fy < fz) {
            b = z
            z = y
            y = a + b - y
            fz = fy
            fy = func(y)
        } else {
            a = y
            y = z
            z = a + b - z
            fy = fz
            fz = func(z)
        }
        k++
    }
    if (debug) {
        println(
            """
            |***********************
            |* Golden Ratio method *
            |***********************
        """.trimMargin())
        println("N:${2 * (k + 1)} K: $k X: ${(a + b) / 2.0}")
    }
    return (a + b) / 2.0
}