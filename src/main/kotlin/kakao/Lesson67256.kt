package kakao

/**
 *     https://school.programmers.co.kr/learn/courses/30/lessons/67256
 *
 *     val lesson = Lesson67256()
 *     val result = lesson.solution(intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5),"right")
 *     println(result)
 */

class Lesson67256 {
    companion object {
        private const val MOVE_RIGHT_HAND = "R"
        private const val MOVE_LEFT_HAND = "L"
        private const val RIGHT_HAND_USER = "right"
    }

    private var isRightHandUser: Boolean = false
    private val moves: MutableList<String> = mutableListOf()
    private var currentLeftHand: Int = -1
    private var currentRightHand: Int = -1

    fun solution(numbers: IntArray, hand: String): String {
        isRightHandUser = hand == RIGHT_HAND_USER

        numbers.forEach {
            when (it) {
                1, 4, 7 -> moveLeftHand(it)
                3, 6, 9 -> moveRightHand(it)
                else -> calcNearHand(it)
            }
        }
        return moves.joinToString("")
    }

    private fun calcNearHand(num: Int) {
        val rightCost = getHandCost(currentRightHand, num)
        val leftCost = getHandCost(currentLeftHand, num)
        when {
            rightCost < leftCost -> moveRightHand(num)
            rightCost > leftCost -> moveLeftHand(num)
            rightCost == leftCost -> if(isRightHandUser) moveRightHand(num) else moveLeftHand(num)
        }
    }

    private fun getHandCost(currentHandNum: Int, targetNum: Int): Int {
        val cost = Math.abs(getFloorNumber(currentHandNum) - getFloorNumber(targetNum))
        return if (isCenter(currentHandNum)) cost else cost + 1
    }

    private fun getFloorNumber(num: Int): Int = when (num) {
        1, 2, 3 -> 4
        4, 5, 6 -> 3
        7, 8, 9 -> 2
        else -> 1
    }

    private fun isCenter(num: Int): Boolean = when (num) {
        2, 5, 8, 0 -> true
        else -> false
    }

    private fun moveLeftHand(num: Int) {
        currentLeftHand = num
        moves.add(MOVE_LEFT_HAND)
    }

    private fun moveRightHand(num: Int) {
        currentRightHand = num
        moves.add(MOVE_RIGHT_HAND)
    }
}