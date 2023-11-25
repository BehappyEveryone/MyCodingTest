package kakao

/**
 *     https://school.programmers.co.kr/learn/courses/30/lessons/81302
 *
 *
 *      P는 응시자가 앉아있는 자리를 의미합니다.
 *      O는 빈 테이블을 의미합니다.
 *      X는 파티션을 의미합니다.
 *
 *     ["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"]
 *     ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"]
 *     ["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"]
 *     ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"]
 *     ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]
 */

class Lesson81302 {
    private var maxRoomNum = 0
    private val room:Array<CharArray> = Array(5) { CharArray(5) }
    private val caseResultList:MutableList<Int> = mutableListOf()

    fun solution(places: Array<Array<String>>): IntArray {
        maxRoomNum = places[0].size

        for (roomRow in places) {
            for (roomCol in roomRow.indices) {
                room[roomCol] = roomRow[roomCol].toCharArray()
            }
            roomCheckProcess()
        }

        return caseResultList.toIntArray()
    }

    private fun roomCheckProcess() {
        for(i in 0..5) {
            for(j in 0..5) {

                if(room[i][j] == 'P') {
//                    room[]
                }
            }
        }
    }
}