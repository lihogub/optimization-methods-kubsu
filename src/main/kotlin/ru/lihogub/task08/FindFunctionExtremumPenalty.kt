package ru.lihogub.task08

import kotlin.math.pow


fun findFunctionExtremumPenalty(
    epsilon: Double,
    stepMultiplier: Double,
    func: (Double, Double) -> Double,
    penaltyFunc: (Double, Double) -> Double,
    searchFuncX: (Double) -> Double,
    searchFuncY: (Double) -> Double
) {
    var iteration = 0
    var rk = 1.0
    var x = searchFuncX(rk)
    var y = searchFuncY(rk)
    var funcValue = func(x, y) + (rk / 2) * penaltyFunc(x, y).pow(2)
    var lambda = rk * penaltyFunc(x, y)
    while ((rk / 2) * penaltyFunc(x, y).pow(2) > epsilon) {
        println("k=$iteration rk=$rk [x1, x2]=[$x,$y] F=$funcValue lambda=$lambda")
        rk *= stepMultiplier
        x = searchFuncX(rk)
        y = searchFuncY(rk)
        funcValue = func(x, y) + (rk / 2) * penaltyFunc(x, y).pow(2)
        lambda = rk * penaltyFunc(x, y)
        iteration++
    }
    println("Iterations=$iteration min[X,Y]=[$x, $y] minF=$funcValue lambda=$lambda penalty=${(rk / 2) * penaltyFunc(x, y).pow(2)}")
}
