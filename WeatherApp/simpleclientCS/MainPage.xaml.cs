using System;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.Maui.Controls;
using Newtonsoft.Json.Linq;

namespace simpleclientCS
{
    public partial class MainPage : ContentPage
    {
        private readonly string _apiKey = ""; 
        public JObject weatherData;

        public MainPage()
        {
            InitializeComponent();
        }

        private async void OnGetWeatherClicked(object sender, EventArgs e)
        {
            string apiKey = ""; 
            string city = CityEntry.Text;

            string apiUrl = $"https://api.weatherapi.com/v1/current.json?key={apiKey}&q={city}&aqi=no";

            using (HttpClient client = new HttpClient())
            {
                try
                {
                    string json = await client.GetStringAsync(apiUrl);
                    JObject weatherData = JObject.Parse(json);
                    string location = $"{weatherData["location"]["name"]}, {weatherData["location"]["region"]}, {weatherData["location"]["country"]}";
                    string temperature = weatherData["current"]["temp_f"].ToString();
                    string condition = weatherData["current"]["condition"]["text"].ToString();
                    string iconUrl = "https:" + weatherData["current"]["condition"]["icon"].ToString();

                    WeatherLabel.Text = $"Location: {location}\nTemperature: {temperature}°F\nCondition: {condition}";

                    // Display the weather icon
                    WeatherIcon.Source = new UriImageSource
                    {
                        Uri = new Uri(iconUrl),
                        CachingEnabled = true,
                        CacheValidity = new TimeSpan(1, 0, 0, 0) 
                    };
                }
                catch (Exception ex)
                {
                    WeatherLabel.Text = "Error: " + ex.Message;
                }
            }
        }


        private async void OnShow3DayForecastClicked(object sender, EventArgs e)
        {
            string city = CityEntry.Text;

            if (string.IsNullOrEmpty(city))
            {
                WeatherLabel.Text = "Please enter a valid city.";
                return;
            }

            string apiUrl = $"https://api.weatherapi.com/v1/forecast.json?key={_apiKey}&q={city}&days=12&aqi=no&alerts=no";

            using (HttpClient client = new HttpClient())
            {
                try
                {
                    HttpResponseMessage response = await client.GetAsync(apiUrl);

                    if (response.IsSuccessStatusCode)
                    {
                        string json = await response.Content.ReadAsStringAsync();
                        JObject weatherData = JObject.Parse(json);

                        JArray forecastArray = (JArray)weatherData["forecast"]["forecastday"];

                        ForecastStackLayout.Children.Clear();

                        foreach (JToken forecast in forecastArray)
                        {
                            string date = forecast["date"].ToString();
                            string maxTemperature = forecast["day"]["maxtemp_f"].ToString();
                            string minTemperature = forecast["day"]["mintemp_f"].ToString();
                            string condition = forecast["day"]["condition"]["text"].ToString();

                            Label forecastLabel = new Label
                            {
                                Text = $"Date: {date}\nHigh: {maxTemperature}°F\nLow: {minTemperature}°F\nCondition: {condition}",
                                FontSize = 14,
                                Margin = new Thickness(0, 0, 0, 10)
                            };

                            ForecastStackLayout.Children.Add(forecastLabel);
                        }

                        ForecastFrame.IsVisible = true;
                    }
                    else
                    {
                        WeatherLabel.Text = $"Error: {response.ReasonPhrase}";
                    }
                }
                catch (HttpRequestException ex)
                {
                    WeatherLabel.Text = $"Error: {ex.Message}";
                }
            }
        }
    }
}
