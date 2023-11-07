using System;
using System.ComponentModel;
using SQLite;
//using CommunityToolkit.Mvvm.ComponentModel;
//using CommunityToolkit.Mvvm.Input;

namespace FitnessTracker.Models
{
    [Table("Exercise")]
    public class Exercise : INotifyPropertyChanged
    {
        [PrimaryKey, AutoIncrement] // Primary key with auto-increment
        public int Id { get; set; }

        [Column("MachineName")] // Specify column name
        public string Machine { get; set; }

        [Column("Weight")]
        public double Weight { get; set; }

        [Column("Repetitions")]
        public int Repetitions { get; set; }

        [Column("DatePerformed")]
        public DateTime DatePerformed { get; set; }

        [Column("ImageFileName")] // Add a column for the image file name
        public string ImageFileName { get; set; }

        public Exercise(string machine, string image)
        {
            Machine = machine;
            ImageFileName = image;
        }
        public Exercise()
        {

        }
        public Exercise(int id, string machine, double weight, int repetitions, DateTime datePerformed)
        {
            Id = id;
            Machine = machine;
            Weight = weight;
            Repetitions = repetitions;
            DatePerformed = datePerformed;
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected void OnPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChangedEventArgs args = new PropertyChangedEventArgs(propertyName);

                PropertyChanged(this, args);
            }
        }
    }
}
