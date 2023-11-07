using AndroidX.Lifecycle;
using FitnessTracker.Views;
namespace FitnessTracker;

public partial class MainPage : ContentPage
{

	public MainPage()
	{
		InitializeComponent();
		
    }


    private async void OnAddButtonClicked(object sender, EventArgs e)
    { 
        await Navigation.PushAsync(new AddExerciseView());
    }
    
    private async void OnDisplayClicked(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new ViewExercisesView());
    }
}
