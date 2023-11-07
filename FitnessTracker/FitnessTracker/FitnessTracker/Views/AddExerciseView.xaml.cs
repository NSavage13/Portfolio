using FitnessTracker.ViewModels;
namespace FitnessTracker.Views;

public partial class AddExerciseView : ContentPage
{
    private AddExerciseViewModel viewModel;
	public AddExerciseView()
	{
		InitializeComponent();
        viewModel = new AddExerciseViewModel(App.exDB);
	}

    

    private void OnWeightStepperValueChanged(object sender, ValueChangedEventArgs e)
    {
        // Update the Weight Label to display the current value of the Weight Stepper
        
        WeightLabel.Text = e.NewValue.ToString();
    }

    private void OnRepetitionsStepperValueChanged(object sender, ValueChangedEventArgs e)
    {
        // Update the Repetitions Label to display the current value of the Repetitions Stepper
        RepetitionsLabel.Text = e.NewValue.ToString();
    }

    private void OnSaveButtonClicked(object sender, EventArgs e)
    {
        try
        {
            // Retrieve values from UI controls
            string exerciseName = ExercisePicker.SelectedItem as string;
            double weight = WeightStepper.Value;
            int repetitions = (int)RepetitionsStepper.Value;
            DateTime datePerformed = DatePerformedPicker.Date;

            // Call the ViewModel method to save the exercise
            viewModel.SaveExercise(exerciseName, weight, repetitions, datePerformed);

            // Optionally, show a success message to the user
            DisplayAlert("Success", "Exercise saved successfully.", "OK");

            // Clear the input fields or navigate to another page as needed
            ExercisePicker.SelectedItem = null;
            WeightStepper.Value = 0;
            RepetitionsStepper.Value = 0;
            DatePerformedPicker.Date = DateTime.Today;
        }
        catch (Exception ex)
        {
            // Handle any exceptions that may occur during data parsing or database insertion
            DisplayAlert("Error", $"An error occurred: {ex.Message}", "OK");
        }
    }


    private Dictionary<string, string> exerciseImageMapping = new Dictionary<string, string>
{
    { "Dumbbell", "dumbbell.jpg" },
    { "Incline Seat", "inclineseat.jpg" },
    { "Leg Press", "legpress.jpg" },
    { "Seated pushdown", "seatedpushdown.jpg" },
    { "Seated row", "seatedrow.jpg" },
    { "Arm curl", "armcurl.jpg" },
    { "Shoulder Press", "shoulderpress.jpg" },
    { "Chest Press", "chestpress.jpg" },
    { "Rotary Torso", "rotarytorso.jpg" },
    { "Lat Pulldown", "latpulldown.jpg" },
    { "Low Back Extension", "lowbackextension.jpg" },
    { "Abdominal Crunch", "abdominalcrunch.jpg" },
    { "Inner Outer Thigh", "innerouterthigh.jpg" },
    { "Seated Leg Curl", "seatedlegcurl.jpg" },
    { "Leg Extension", "legextension.jpg" },
    { "Resistance Bands", "resistancebands.jpg" }
};


    private void OnExerciseNameTapped(object sender, EventArgs e)
    {
        // Show the exercise picker
        ExercisePicker.Focus();
    }

    // Handle the exercise selection from the picker
    private void OnExercisePickerSelectedIndexChanged(object sender, EventArgs e)
    {
        if (ExercisePicker.SelectedIndex >= 0)
        {
            // Get the selected exercise name
            string selectedExercise = ExercisePicker.SelectedItem as string;

            // Check if the selected exercise exists in the dictionary
            if (exerciseImageMapping.ContainsKey(selectedExercise))
            {
                // Load the corresponding image file name from the dictionary
                string imageFileName = exerciseImageMapping[selectedExercise];

                // Set the image source
                ExerciseImage.Source = imageFileName;
            }
            else
            {
                // Handle the case where there is no image for the selected exercise
                ExerciseImage.Source = ""; // Clear the image
            }
        }
        else
        {
            // Clear the image if no exercise is selected
            ExerciseImage.Source = "";
        }
    }



}
