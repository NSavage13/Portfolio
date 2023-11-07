using FitnessTracker.ViewModels;
namespace FitnessTracker;

public partial class App : Application
{

	public static ExerciseDatabase exDB { get; private set; }
	public static AddExerciseViewModel addViewModel { get; private set; }
	public App(ExerciseDatabase database)
	{
		InitializeComponent();

        exDB = database;
		addViewModel = new AddExerciseViewModel(exDB);
        MainPage = new AppShell();
		
	}
}

