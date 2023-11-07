using System;
using FitnessTracker.Models;
namespace FitnessTracker.ViewModels
{
	public class AddExerciseViewModel
	{
		private Exercise _exercise;
		private ExerciseDatabase _exerciseDataBase;

		public List<Exercise> Workouts { get; set; }
		public Exercise Exercises
		{
			get { return _exercise; }
			set { _exercise = value; }
		}
		public AddExerciseViewModel(ExerciseDatabase exerciseDataBase)
		{
            _exercise = new Exercise();
            _exerciseDataBase = new ExerciseDatabase("exercise.db3");
            _exerciseDataBase = exerciseDataBase;
		}

        public void SaveExercise(string exerciseName, double weight, int repetitions, DateTime datePerformed)
        {
            try
            {
                // Create an Exercise object with the provided values
                Exercise exercise = new Exercise
                {
                    Machine = exerciseName,
                    Weight = weight,
                    Repetitions = repetitions,
                    DatePerformed = datePerformed
                };

                // Call the database method to insert the exercise
                App.exDB.InsertExercise(exercise);

            }
            catch(Exception ex)
            {
                App.Current.MainPage.DisplayAlert("Error", $"An error occurred: {ex.Message}", "OK");
            }

        }
    }
}

