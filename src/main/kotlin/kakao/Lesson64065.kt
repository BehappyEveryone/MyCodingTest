package kakao

/**
 *     https://school.programmers.co.kr/learn/courses/30/lessons/64065
 *
 *     val lesson = Lesson64065()
 *     val result = lesson.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")
 *     println(result)
 */

class Lesson64065 {
    fun solution(s: String): IntArray {
        return s.substring(2 until s.length-2)
            .split("},{")
            .map { it.split(",").map { num -> num.toInt() } }
            .sortedBy { it.size }
            .fold(setOf<Int>()){ acc, ints -> acc.union(ints) }
            .toIntArray()
    }
}