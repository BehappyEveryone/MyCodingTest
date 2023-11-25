package test

class Test1 {

    private lateinit var friendList: List<String>
    private lateinit var giftList: List<Pair<String,String>>
    private lateinit var giftMap: Array<IntArray>
    private lateinit var giftTotalArray: IntArray
    private lateinit var nextMonthGiftArray: IntArray

    private var maxCount:Int = 0
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        friendList = friends.toList()
        giftList = gifts.map { it.split(" ") }.map { Pair(it.first(),it.last()) }
        giftMap = Array(gifts.size) { IntArray(gifts.size) { 0 } }
        giftTotalArray = IntArray(gifts.size) { 0 }
        nextMonthGiftArray = IntArray(gifts.size) { 0 }
        processTask()
        return maxCount
    }

    private fun processTask() {
        giftList.forEach {
            giftMap[friendList.indexOf(it.first)][friendList.indexOf(it.second)]++
            giftTotalArray[friendList.indexOf(it.first)]++
            giftTotalArray[friendList.indexOf(it.second)]--
        }

        friendList.forEachIndexed { sendIdx, iStr ->
            friendList.forEachIndexed { receiveIdx, jStr ->
                val sendCount = giftMap[sendIdx][receiveIdx]
                val receiveCount = giftMap[receiveIdx][sendIdx]

                when {
                    sendCount > receiveCount -> nextMonthGiftArray[sendIdx]++
                    sendCount == receiveCount -> {
                        if(giftTotalArray[sendIdx] > giftTotalArray[receiveIdx]) {
                            nextMonthGiftArray[sendIdx]++
                        }
                    }
                }
            }
        }

        maxCount = nextMonthGiftArray.maxOf { it }
    }


}