package task04

import kotlin.math.pow
import kotlin.math.sqrt

fun findFunctionExtremumGradientDescent(
    epsilon: Double,
    delta: Double,
    x0: Double,
    y0: Double,
    func: (Double, Double) -> Double,
    gradX: (Double, Double) -> Double,
    gradY: (Double, Double) -> Double
) {
    var x = x0
    var y = y0

    var dx = gradX(x, y)
    var dy = gradY(x, y)

    while (sqrt(dx.pow(2) + dy.pow(2)) > epsilon) {
        x -= delta * dx
        y -= delta * dy

        dx = gradX(x, y)
        dy = gradY(x, y)

        println("$x $y $dx $dy")
    }

    println("Extremum: ($x, $y)")
    println("Extremum value: ${func(x, y)}")
}