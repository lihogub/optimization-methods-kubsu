import task01.findFunctionExtremumDichotomy
import task02.findFunctionExtremumGoldenRatio

fun main() {
    val a = -3.0
    val b = 7.0
    val delta = 0.2
    val epsilon = 0.5
    val f: (Double)->Double = {x -> 2*x*x + 2*x + 3.5}
    findFunctionExtremumDichotomy(epsilon, delta, a, b, f)
    findFunctionExtremumGoldenRatio(epsilon, a, b, f)
}
