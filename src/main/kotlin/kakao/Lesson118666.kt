package kakao

/**
 *     https://school.programmers.co.kr/learn/courses/30/lessons/118666
 *
 *     val solution = Lesson118666()
 *     val survey = arrayOf("AN", "CF", "MJ", "RT", "NA")
 *     val choices = intArrayOf(5, 3, 2, 7, 5)
 *     val result = solution.solution(survey, choices)
 *     println(result)
 */

class Lesson118666 {
    private val typeHash: HashMap<Char, Int> = hashMapOf(
        'R' to 0, 'T' to 0,
        'C' to 0, 'F' to 0,
        'J' to 0, 'M' to 0,
        'A' to 0, 'N' to 0,
    )

    fun solution(survey: Array<String>, choices: IntArray): String {
        choices.forEachIndexed { index, choice ->
            val typeChar = if (isRightType(choice)) survey[index].last() else survey[index].first()
            val newScore: Int = typeHash[typeChar]?.plus(getScore(choice)) ?: getScore(choice)
            typeHash[typeChar] = newScore
        }
        return getAllType()
    }

    private fun getAllType(): String {
        val firstChar = compareToType('R', 'T')
        val secondChar = compareToType('C', 'F')
        val thirdChar = compareToType('J', 'M')
        val fourthChar = compareToType('A', 'N')
        return "$firstChar$secondChar$thirdChar$fourthChar"
    }

    private fun compareToType(firstChar: Char, secondChar: Char): Char {
        return getHigherType(Pair(firstChar, typeHash[firstChar]!!), Pair(secondChar, typeHash[secondChar]!!))
    }

    private fun isRightType(chooseNumber: Int): Boolean = chooseNumber > 4

    private fun getScore(chooseNumber: Int): Int = when (chooseNumber) {
        1, 7 -> 3
        2, 6 -> 2
        3, 5 -> 1
        else -> 0
    }

    private fun getHigherType(firstType: Pair<Char, Int>, secondType: Pair<Char, Int>): Char {
        return when {
            firstType.second > secondType.second -> firstType.first
            firstType.second < secondType.second -> secondType.first
            else -> getDictionaryOrder(firstType.first, secondType.first)
        }
    }

    private fun getDictionaryOrder(firstChar: Char, secondChar: Char) =
        if (firstChar < secondChar) firstChar else secondChar
}