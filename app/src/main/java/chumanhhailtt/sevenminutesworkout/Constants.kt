package chumanhhailtt.sevenminutesworkout

object Constants {
    var exercises: ArrayList<Exercise> = ArrayList<Exercise>()

    init {
        exercises.add(Exercise("Jumping Jack", R.drawable.ic_jumping_jacks, 30, false, false))
        exercises.add(Exercise("Abdominal Crunch", R.drawable.ic_abdominal_crunch, 30, false, false))
        exercises.add(Exercise("High knees running in place", R.drawable.ic_high_knees_running_in_place, 30, false, false))
    }
}