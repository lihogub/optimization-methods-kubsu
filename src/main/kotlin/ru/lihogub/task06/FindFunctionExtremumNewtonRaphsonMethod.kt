package ru.lihogub.task06

import ru.lihogub.common.measureVector
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.linalg.inv
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.D1Array
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.*
import ru.lihogub.task02.findFunctionExtremumGoldenRatio
import kotlin.math.abs

fun findFunctionExtremumNewtonRaphsonMethod(
    epsilon1: Double,
    epsilon2: Double,
    delta: Double,
    x0: Double,
    y0: Double,
    func: (Double, Double) -> Double,
    gradX: (Double, Double) -> Double,
    gradY: (Double, Double) -> Double,
    hessian: D2Array<Double>
) {
    var vectorX = mk.ndarray(listOf(x0, y0))
    val inverseHessian = mk.linalg.inv(hessian)

    var vectorGrad = mk.ndarray(listOf(gradX(vectorX[0], vectorX[1]), gradY(vectorX[0], vectorX[1])))
    var vectorD: D1Array<Double>

    var k = 0

    while (measureVector(vectorGrad) > epsilon1) {
        vectorD =
            if (inverseHessian[0, 0] > 0.0 && ((inverseHessian[0, 0] * inverseHessian[1, 1] - inverseHessian[0, 1] * inverseHessian[0, 1]) > 0.0)) {
                -inverseHessian.dot(vectorGrad)
            } else {
                -vectorGrad
            }

        val t = findFunctionExtremumGoldenRatio(epsilon1, 0.0, 10.0, { x ->
            func(
                vectorX[0] + x * vectorD[0],
                vectorX[1] + x * vectorD[1]
            )
        })


        val newVectorX = vectorX + vectorD * t

        if (measureVector(newVectorX - vectorX) < epsilon2 && abs(
                func(vectorX[0], vectorX[1]) - func(
                    newVectorX[0],
                    newVectorX[1]
                )
            ) < epsilon2
        ) {
            break
        }

        vectorX = newVectorX

        vectorGrad = mk.ndarray(listOf(gradX(vectorX[0], vectorX[1]), gradY(vectorX[0], vectorX[1])))
        k++
        println("k=$k t=$t [x, y]=${vectorX} [dx, dy]=${vectorGrad} f(x)=${func(vectorX[0], vectorX[1])}")
    }

    println("x*: (${vectorX})")
    println("f(x*): ${func(vectorX[0], vectorX[1])}")
}
