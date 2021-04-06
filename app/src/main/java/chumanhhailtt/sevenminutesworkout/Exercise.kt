package chumanhhailtt.sevenminutesworkout

//data class Exercise (
//    var id: Int,
//    var name: String,
//    var image: Int,
//    var length: Int,
//    var isCompleted: Boolean,
//    var isSelected: Boolean
//)

class Exercise (
    var name: String,
    var image: Int,
    var length: Int,
    var isCompleted: Boolean,
    var isSelected: Boolean
) {
    companion object {
        var num = 0
    }

    var id: Int = num

    init {
        num++
    }
}

