package ru.lihogub.task03

fun findFunctionExtremumFibonacci(epsilon: Double, leftBound: Double, rightBound: Double, func: (Double) -> Double) {
    var a = leftBound
    var b = rightBound

    var n = 1
    var fibonacciNMinusOne = 1
    var fibonacciN = 1

    val fibonacciList = mutableListOf(1)

    while ((b - a) / epsilon > fibonacciN) {
        val tmp = fibonacciN
        fibonacciN += fibonacciNMinusOne
        fibonacciNMinusOne = tmp
        fibonacciList.add(fibonacciN)
        ++n
    }

    var y = a + (1.0 * (fibonacciN - fibonacciNMinusOne) / fibonacciN) * (b - a)
    var z = a + b - y
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
    println(
        """
            |********************
            |* Fibonacci method *
            |********************
        """.trimMargin())
    println("Used fibonacci numbers: $fibonacciList")
    println("N:${2 * (k + 1)} K: $k X: ${(a + b) / 2.0}")
}