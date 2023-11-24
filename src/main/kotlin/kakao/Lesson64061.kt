package kakao

import java.util.*

/**
 *     https://school.programmers.co.kr/learn/courses/30/lessons/64061
 *     val lesson = Lesson64061()
 *
 *     val result = lesson.solution(
 *         arrayOf(
 *             intArrayOf(0, 0, 0, 0, 0),
 *             intArrayOf(0, 0, 1, 0, 3),
 *             intArrayOf(0, 2, 5, 0, 1),
 *             intArrayOf(4, 2, 4, 4, 2),
 *             intArrayOf(3, 5, 1, 3, 1),
 *         ), intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)
 *     )
 *
 *     println(result)
 */

class Lesson64061 {

    companion object {
        private const val EMPTY_DOLL = -1
    }

    private lateinit var worksList: List<Int>
    private val boardStackList: MutableList<Stack<Int>> = mutableListOf()
    private val basketStack: Stack<Int> = Stack()
    private var removedDollCount: Int = 0

    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        worksList = moves.toList()

        for (i in board.indices) boardStackList.add(Stack())

        board.reversed().forEach {
            it.forEachIndexed { index, doll ->
                if (doll != 0) boardStackList[index].push(doll)
            }
        }

        processTask()

        return removedDollCount
    }

    private fun processTask() {
        worksList.forEach {
            val idx = it - 1
            if (!boardStackList[idx].isEmpty()) {
                val selectedDoll = boardStackList[idx].pop()
                val peekBasketDoll = if(basketStack.isEmpty()) EMPTY_DOLL else basketStack.peek()

                if (peekBasketDoll == selectedDoll) removeBasketDoll() else basketStack.push(selectedDoll)
            }
        }
    }

    private fun removeBasketDoll() {
        basketStack.pop()
        removedDollCount+=2
    }
}