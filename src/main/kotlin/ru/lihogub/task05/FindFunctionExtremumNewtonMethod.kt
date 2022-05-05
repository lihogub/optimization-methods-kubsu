package ru.lihogub.task05

import ru.lihogub.common.measureVector
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.linalg.inv
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.minusAssign

fun findFunctionExtremumNewtonMethod(
    epsilon: Double,
    delta: Double,
    x0: Double,
    y0: Double,
    func: (Double, Double) -> Double,
    gradX: (Double, Double) -> Double,
    gradY: (Double, Double) -> Double,
    hessian: D2Array<Double>
) {
    val vectorX = mk.ndarray(listOf(x0, y0))

    var vectorGrad = mk.ndarray(listOf(gradX(vectorX[0], vectorX[1]), gradY(vectorX[0], vectorX[1])))

    val inverseHessian = mk.linalg.inv(hessian)

    var k = 0
    while (measureVector(vectorGrad) > epsilon) {
        vectorX -= inverseHessian.dot(vectorGrad)
        vectorGrad = mk.ndarray(listOf(gradX(vectorX[0], vectorX[1]), gradY(vectorX[0], vectorX[1])))
        k++
        println("k=$k [x, y]=${vectorX} [dx, dy]=${vectorGrad}")
    }

    println("x*: (${vectorX})")
    println("f(x*): ${func(vectorX[0], vectorX[1])}")
}
