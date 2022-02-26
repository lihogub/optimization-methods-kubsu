import task01.findFunctionExtremum

fun main() {
    val a = -3.0
    val b = 7.0
    val delta = 0.2
    val epsilon = 0.5
    val f: (Double)->Double = {x -> 2*x*x + 2*x + 3.5}
    findFunctionExtremum(epsilon, delta, a, b, f)
}