import kakao.Lesson67257
import test.Test1

fun main(args: Array<String>) {
    val lesson = Test1()

    val result = lesson.solution(arrayOf("joy", "brad", "alessandro", "conan", "david"), arrayOf(
        "alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"
    ))
    println(result)
}