using Microsoft.Maui.Controls;
using FitnessTracker.ViewModels;
using FitnessTracker.Models;
using AndroidX.Lifecycle;

namespace FitnessTracker.Views
{
    public partial class ViewExercisesView : ContentPage
    {
        private ViewExercisesViewModel viewModel;
        public ViewExercisesView()
        {
            InitializeComponent();


            viewModel = new ViewExercisesViewModel(App.exDB);

            // Set the binding context to the ViewModel
            BindingContext = viewModel;
        }

        private void OnDeleteExerciseClicked(object sender, EventArgs e)
        {
            if (sender is MenuItem menuItem && menuItem.CommandParameter is Exercise exerciseToDelete)
            {
                viewModel.DeleteExercise(exerciseToDelete);
            }
        }

        private void OnDateFilterSelected(object sender, DateChangedEventArgs e)
        {
            // Retrieve the selected date from the DatePicker
            DateTime selectedDate = e.NewDate;

            // Filter the list of exercises by date and update the UI
            viewModel.FilterExercisesByDate(selectedDate);
        }

        private void OnMachineFilterSelectedIndexChanged(object sender, EventArgs e)
        {
            // Retrieve the selected machine from the Picker
            string selectedMachine = MachineFilterPicker.SelectedItem as string;

            viewModel.FilterExercisesByMachine(selectedMachine);
        }





    }
}
