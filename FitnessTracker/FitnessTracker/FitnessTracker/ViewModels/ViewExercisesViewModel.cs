using System.Collections.Generic;
using FitnessTracker.Models;
using System.ComponentModel;
using System.Runtime.CompilerServices;

namespace FitnessTracker.ViewModels
{
    public class ViewExercisesViewModel : INotifyPropertyChanged
    {
        private ExerciseDatabase exerciseDatabase;
        private List<Exercise> exercises;

        private bool isListVisible = false;
        public bool IsListVisible
        {
            get { return isListVisible; }
            set
            {
                if (isListVisible != value)
                {
                    isListVisible = value;
                    OnPropertyChanged(nameof(IsListVisible));
                }
            }
        }

        public List<Exercise> Exercises
        {
            get { return exercises; }
            set
            {
                if (exercises != value)
                {
                    exercises = value;
                    OnPropertyChanged();
                }
            }
        }

        public ViewExercisesViewModel(ExerciseDatabase db)
        {
            exerciseDatabase = db;
            RefreshList();
            IsListVisible = false;// Initial retrieval of exercises from the database
        }

        public async void DeleteExercise(Exercise exercise)
        {
            // Prompt the user for confirmation (optional)
            bool userConfirmed = await Application.Current.MainPage.DisplayAlert("Delete Exercise", "Are you sure you want to delete this exercise?", "Yes", "Cancel");

            if (userConfirmed)
            {
                Exercises.Remove(exercise);
                exerciseDatabase.DeleteExercise(exercise);
            }
        }

        private void RefreshList()
        {
            Exercises = exerciseDatabase.GetExercise();
        }

        public void FilterExercisesByDate(DateTime selectedDate)
        {
            // Filter the exercises by date and update the UI
            Exercises = exerciseDatabase.GetExercisesByDate(selectedDate); // Implement GetExercisesByDate in your database class
            OnPropertyChanged(nameof(Exercises));
            IsListVisible = true;
        }

        public void FilterExercisesByMachine(string selectedMachine)
        {
            // Filter the exercises by machine and update the UI
            Exercises = exerciseDatabase.GetExercisesByMachine(selectedMachine); // Implement GetExercisesByMachine in your database class
            OnPropertyChanged(nameof(Exercises));
            IsListVisible = true;
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        
    }
}
