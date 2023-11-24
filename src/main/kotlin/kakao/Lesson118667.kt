package kakao

/**
 *     https://school.programmers.co.kr/learn/courses/30/lessons/118667
 *
 *     val solution = Lesson118667()
 *     val queue1 = intArrayOf(3, 2, 7, 2)
 *     val queue2 = intArrayOf(4, 6, 5, 1)
 *     val result = solution.solution(queue1, queue2)
 *     println(result)
 */

class Lesson118667 {

    private val firstQueue: ArrayDeque<Long> = ArrayDeque()
    private val secondQueue: ArrayDeque<Long> = ArrayDeque()
    private var firstQueueSum: Long = 0
    private var secondQueueSum: Long = 0
    private var max: Int = 0
    private var count: Int = 0

    fun solution(queue1: IntArray, queue2: IntArray): Int {
        firstQueue.addAll(queue1.map { it.toLong() })
        secondQueue.addAll(queue2.map { it.toLong() })
        firstQueueSum = firstQueue.sum()
        secondQueueSum = secondQueue.sum()
        max = queue1.size * 3

        feasibilityCheck()

        return count
    }

    private fun feasibilityCheck() {
        if(isOddNumber()) processImpossible()

        while (count <= max) {
            when {
                isSameCheck() -> return
                firstQueueSum > secondQueueSum -> processTask(true)
                firstQueueSum < secondQueueSum -> processTask(false)
            }
        }
        processImpossible()
    }

    private fun processTask(isFirstHigher: Boolean) {
        if (isFirstHigher) {
            val polledItem = queueUpdate(firstQueue, secondQueue)
            firstQueueSum -= polledItem
            secondQueueSum += polledItem
        } else {
            val polledItem = queueUpdate(secondQueue, firstQueue)
            secondQueueSum -= polledItem
            firstQueueSum += polledItem
        }
    }

    private fun queueUpdate(higherQueue: ArrayDeque<Long>, lowerQueue: ArrayDeque<Long>): Long {
        val polledItem = higherQueue.removeFirst()
        lowerQueue.addLast(polledItem)
        count++
        return polledItem
    }

    private fun processImpossible() {
        count = -1
    }

    private fun isOddNumber(): Boolean = (firstQueueSum + secondQueueSum) % 2 == 1.toLong()

    private fun isSameCheck() = firstQueueSum == secondQueueSum
}