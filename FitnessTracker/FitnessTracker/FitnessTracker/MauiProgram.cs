using Microsoft.Extensions.Logging;
using FitnessTracker.ViewModels;
namespace FitnessTracker;

public static class MauiProgram
{
	public static MauiApp CreateMauiApp()
	{
		var builder = MauiApp.CreateBuilder();
		builder
			.UseMauiApp<App>()
			.ConfigureFonts(fonts =>
			{
				fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
				fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
			});
        string dbPath = System.IO.Path.Combine(FileSystem.AppDataDirectory, "exercise.db3");
        builder.Services.AddSingleton<ExerciseDatabase>(s => ActivatorUtilities.CreateInstance<ExerciseDatabase>(s, dbPath));
        
        return builder.Build();
	}
}

