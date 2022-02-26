package task01

import kotlin.math.abs

fun findFunctionExtremum(epsilon: Double, delta: Double, leftBound: Double, rightBound: Double, func: (Double) -> Double) {
    var leftPoint = leftBound
    var rightPoint = rightBound
    var middleLeftPoint: Double
    var middleRightPoint: Double
    var k = -1

    while (abs(leftPoint - rightPoint) > epsilon) {
        middleLeftPoint = (leftPoint + rightPoint - delta) / 2.0
        middleRightPoint = (leftPoint + rightPoint + delta) / 2.0

        val middleLeftValue = func(middleLeftPoint)
        val middleRightValue = func(middleRightPoint)

        if (middleLeftValue > middleRightValue) {
            leftPoint = middleLeftPoint
        } else {
            rightPoint = middleRightPoint
        }
        k++
    }
    println("N:${2 * (k + 1)} K: $k X: ${(leftPoint + rightPoint) / 2.0}")
}