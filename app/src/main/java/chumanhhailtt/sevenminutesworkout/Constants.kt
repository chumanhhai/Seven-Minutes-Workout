package chumanhhailtt.sevenminutesworkout

object Constants {
    var exercises: ArrayList<Exercise> = ArrayList<Exercise>()

    init {
        exercises.add(Exercise("Jumping Jack", R.drawable.ic_jumping_jacks, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Abdominal Crunch", R.drawable.ic_abdominal_crunch, 20, Exercise.NOT_YET))
        exercises.add(Exercise("High knees running in place", R.drawable.ic_high_knees_running_in_place, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Lunge", R.drawable.ic_lunge, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Plank", R.drawable.ic_plank, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Side Plank", R.drawable.ic_side_plank, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Push up", R.drawable.ic_push_up, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Push up and Rotate", R.drawable.ic_push_up_and_rotation, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Squat", R.drawable.ic_squat, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Step up onto chair", R.drawable.ic_step_up_onto_chair, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Triceps dip on chair", R.drawable.ic_triceps_dip_on_chair, 20, Exercise.NOT_YET))
        exercises.add(Exercise("Wall sit", R.drawable.ic_wall_sit, 20, Exercise.NOT_YET))
    }
}