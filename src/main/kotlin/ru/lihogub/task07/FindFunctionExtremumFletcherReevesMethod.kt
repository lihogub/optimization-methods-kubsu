package ru.lihogub.task07

import ru.lihogub.common.measureVector
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.*
import ru.lihogub.task02.findFunctionExtremumGoldenRatio
import kotlin.math.abs
import kotlin.math.pow

fun findFunctionExtremumFletcherReevesMethod(
    epsilon1: Double,
    epsilon2: Double,
    delta: Double,
    x0: Double,
    y0: Double,
    func: (Double, Double) -> Double,
    gradX: (Double, Double) -> Double,
    gradY: (Double, Double) -> Double
) {
    var vectorX = mk.ndarray(listOf(x0, y0))

    var vectorGrad = mk.ndarray(listOf(gradX(vectorX[0], vectorX[1]), gradY(vectorX[0], vectorX[1])))
    var vectorD = - vectorGrad
    var b = 0.0
    var k = 0
    while (measureVector(vectorGrad) > epsilon1) {

        vectorD = -vectorGrad + vectorD * b

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

        val newVectorGrad = mk.ndarray(listOf(gradX(newVectorX[0], newVectorX[1]), gradY(newVectorX[0], newVectorX[1])))

        b = (measureVector(newVectorGrad).pow(2)) / (measureVector(vectorGrad).pow(2))

        k++
        println("k=$k t=$t b=$b [x, y]=$vectorX f(x)=${func(vectorX[0], vectorX[1])}")

        vectorGrad = newVectorGrad
        vectorX = newVectorX
    }

    println("x*: (${vectorX})")
    println("f(x*): ${func(vectorX[0], vectorX[1])}")
}
