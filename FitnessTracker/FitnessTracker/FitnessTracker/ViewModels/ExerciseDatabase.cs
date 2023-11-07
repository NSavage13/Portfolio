using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SQLite;
using FitnessTracker.Models;
namespace FitnessTracker.ViewModels
{
	public class ExerciseDatabase
	{
        private SQLiteConnection _connection;
        private string _dbPath;
        private void Init()
        {   //build the connection to the database
            if (_connection != null)
            {
                return;
            }
            _connection = new SQLiteConnection(_dbPath);
            _connection.CreateTable<Exercise>();

        }
        public ExerciseDatabase(string dbPath)
        {
            _dbPath = dbPath;//set database name
        }

        public async void InsertExercise(Exercise newExercise)
        {
            int result = 0;
            try
            {
                Init();
                if (string.IsNullOrEmpty(newExercise.Machine))
                {
                    //protect my data
                    throw new Exception("Name Required");
                }//end if
                //check everything that could be an issue
                result = _connection.Insert(newExercise);
            }//end try
            catch (Exception ex)
            {
                await App.Current.MainPage.DisplayAlert("DB Error", ex.ToString(), "OK");
            }
        }

        public List<Exercise> GetExercise()
        {
            try
            {
                Init();
                return _connection.Table<Exercise>().OrderBy(p => p.DatePerformed).ToList();
            }
            catch (Exception ex)
            {
                App.Current.MainPage.DisplayAlert("Read error", ex.ToString(), "OK");
            }

            //will return blank if the database fails
            return new List<Exercise>();
        }

        public void DeleteExercise(Exercise exercise)
        {
            try
            {
                Init();
                _connection.Delete(exercise);
            }
            catch (Exception ex)
            {
                App.Current.MainPage.DisplayAlert("Delete Error", ex.ToString(), "OK");
            }
        }

        public List<Exercise> GetExercisesByDate(DateTime selectedDate)
        {
            // Retrieve all exercises from the database
            var allExercises = _connection.Table<Exercise>().ToList();

            // Filter the exercises in memory by date
            var filteredExercises = allExercises.Where(exercise => exercise.DatePerformed.Date == selectedDate.Date).ToList();

            return filteredExercises;
        }

        public List<Exercise> GetExercisesByMachine(string machineName)
        {
            // Check if the machineName is "none"
            if (machineName.ToLower() == "---")
            {
                // Return all exercises
                return _connection.Table<Exercise>().ToList();
            }
            else
            {
                // Return exercises with a matching Machine name
                return _connection.Table<Exercise>().Where(exercise => exercise.Machine == machineName).ToList();
            }
        }

    }
}

