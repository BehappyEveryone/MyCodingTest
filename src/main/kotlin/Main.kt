import kakao.Lesson64061

fun main(args: Array<String>) {
    val lesson = Lesson64061()

    val result = lesson.solution(
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 3),
            intArrayOf(0, 2, 5, 0, 1),
            intArrayOf(4, 2, 4, 4, 2),
            intArrayOf(3, 5, 1, 3, 1),
        ), intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)
    )

    println(result)
}