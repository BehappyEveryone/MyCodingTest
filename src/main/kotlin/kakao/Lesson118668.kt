package kakao

/**
 *     https://school.programmers.co.kr/learn/courses/30/lessons/118668
 *
 *     val solution = Lesson118668()
 *     val result = solution.solution(0,0, arrayOf(
 *         intArrayOf(0, 0, 2, 1, 2),
 *         intArrayOf(4, 5, 3, 1, 2),
 *         intArrayOf(4, 11, 4, 0, 2),
 *         intArrayOf(10, 4, 0, 4, 2),
 *     ))
 *     println(result)
 */

class Lesson118668 {
    private lateinit var dp: Array<IntArray>
    private lateinit var problemsList: List<IntArray>
    private var targetAlp: Int = 0
    private var targetCop: Int = 0

    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        problemsList = problems.toList()
        targetAlp = Math.max(alp, problemsList.maxOf { it[0] })
        targetCop = Math.max(cop, problemsList.maxOf { it[1] })
        dp = Array(targetAlp + 2) { IntArray(targetCop + 2) { Int.MAX_VALUE } }
        solve(alp, cop)
        return dp[targetAlp][targetCop]
    }

    private fun solve(startAlp: Int, startCop: Int) {

        dp[startAlp][startCop] = 0

        for (i in startAlp..targetAlp) {
            for (j in startCop..targetCop) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1)
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1)

                checkProblemSolve(i, j)
            }
        }
    }

    private fun checkProblemSolve(i: Int, j: Int) {
        problemsList.forEach {
            val reqAlp = it[0]
            val reqCop = it[1]
            val rwdAlp = it[2]
            val rwdCop = it[3]
            val cost = it[4]
            if (reqAlp <= i && reqCop <= j) {
                val alp = if (targetAlp < rwdAlp + i) targetAlp else rwdAlp + i
                val cop = if (targetCop < rwdCop + j) targetCop else rwdCop + j
                dp[alp][cop] = Math.min(dp[i][j] + cost, dp[alp][cop])
            }
        }
    }
}