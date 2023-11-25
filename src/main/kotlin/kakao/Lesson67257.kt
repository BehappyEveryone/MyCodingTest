package kakao

/**
 *     https://school.programmers.co.kr/learn/courses/30/lessons/67257
 *
 *     val lesson = Lesson67257()
 *     val result = lesson.solution("100-200*300-500+20")
 *     println(result)
 */

class Lesson67257 {

    private lateinit var numberList: List<Long>
    private lateinit var operatorList: List<Char>
    private val tempNumberList: MutableList<Long> = mutableListOf()
    private val tempOperatorList: MutableList<Char> = mutableListOf()
    private val caseList: List<String> = listOf("+-*", "+*-", "-+*", "-*+", "*-+", "*+-")
    private var maxNum: Long = 0

    fun solution(expression: String): Long {
        val regex = Regex("\\-|\\*|\\+")
        numberList = expression.split(regex).map { it.toLong() }
        operatorList = expression.filter { it in setOf('+', '-', '*') }.toList()

        calcMaxNum()
        return maxNum
    }
    private fun calcMaxNum() {
        for (i in caseList) {
            initTemp()
            calcOperation(i[0])
            calcOperation(i[1])
            calcOperation(i[2])
            val calcNum = tempNumberList.fold(0L) { acc, num -> acc + num }
            maxNum = Math.max(maxNum, Math.abs(calcNum))
        }
    }
    private fun initTemp() {
        tempNumberList.run {
            clear()
            addAll(numberList.toMutableList())
        }
        tempOperatorList.run {
            clear()
            addAll(operatorList.toMutableList())
        }
    }

    private fun calcOperation(operator: Char) {
        var isContain = tempOperatorList.contains(operator)
        while (isContain) {
            val opeIdx = tempOperatorList.indexOfFirst { it == operator }
            tempOperatorList.removeAt(opeIdx)
            isContain = tempOperatorList.contains(operator)

            tempNumberList.let {
                val calcResult = calcWithOperator(operator, it[opeIdx], it[opeIdx + 1])
                it.add(opeIdx, calcResult)
                it.removeAt(opeIdx + 1)
                it.removeAt(opeIdx + 1)
            }
        }
    }
    private fun calcWithOperator(operator: Char, firstNum: Long, secondNum: Long): Long =
        when (operator) {
            '+' -> firstNum + secondNum
            '-' -> firstNum - secondNum
            '*' -> firstNum * secondNum
            else -> throw IllegalAccessException()
        }
}