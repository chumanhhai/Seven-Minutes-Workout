package chumanhhailtt.sevenminutesworkout

class Exercise (
    var name: String,
    var image: Int,
    var length: Int,
    var status: Int
) {
    companion object {
        var num = 0
        val DONE = 0
        val DOING = 1
        val NOT_YET = 2
    }

    var id: Int = num

    init {
        num++
    }
}

