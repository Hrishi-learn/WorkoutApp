package com.hrishi.workoutapp

object Constants {
    fun getExercises():ArrayList<ExerciseModel>{
        var exercises=ArrayList<ExerciseModel>()

        var exercise1=ExerciseModel("Abdominal Crunch",1,R.drawable.ic_abdominal_crunch,false,false)
        exercises.add(exercise1)
        var exercise2=ExerciseModel("High Knees",2,R.drawable.ic_high_knees_running_in_place,false,false)
        exercises.add(exercise2)
        var exercise3=ExerciseModel("lunge",3,R.drawable.ic_lunge,false,false)
        exercises.add(exercise3)
        var exercise4=ExerciseModel("plank",4,R.drawable.ic_plank,false,false)
        exercises.add(exercise4)
        var exercise5=ExerciseModel("push up",5,R.drawable.ic_push_up,false,false)
        exercises.add(exercise5)
        var exercise6=ExerciseModel("side plank",6,R.drawable.ic_side_plank,false,false)
        exercises.add(exercise6)
        var exercise7=ExerciseModel("squat",7,R.drawable.ic_squat,false,false)
        exercises.add(exercise7)
        var exercise8=ExerciseModel("step Up",8,R.drawable.ic_step_up_onto_chair,false,false)
        exercises.add(exercise8)
        var exercise9=ExerciseModel("triceps",9,R.drawable.ic_triceps_dip_on_chair,false,false)
        exercises.add(exercise9)
        var exercise10=ExerciseModel("Wall Sit",10,R.drawable.ic_wall_sit,false,false)
        exercises.add(exercise10)
        var exercise11=ExerciseModel("push up and rotate",11,R.drawable.ic_push_up_and_rotation,false,false)
        exercises.add(exercise11)
        return exercises
    }
}