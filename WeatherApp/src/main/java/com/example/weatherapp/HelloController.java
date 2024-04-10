package com.example.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class HelloController {

    @FXML
    private TextField cityTextField;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label maxTemperatureLabel;

    @FXML
    private Label minTemperatureLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label countryCodeLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private Label windDegreeLabel;

    @FXML
    protected void onHelloButtonClick() {
        String cityName = cityTextField.getText();
        if (!cityName.isEmpty()) {
            try {

                JSONObject weatherData = WeatherAPI.getWeatherData(cityName);


                BigDecimal temperature = weatherData.getJSONObject("main").getBigDecimal("temp");
                BigDecimal maxTemp = weatherData.getJSONObject("main").getBigDecimal("temp_max");
                BigDecimal minTemp = weatherData.getJSONObject("main").getBigDecimal("temp_min");
                BigDecimal humidity = weatherData.getJSONObject("main").getBigDecimal("humidity");
                String description = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");
                String countryCode = weatherData.getJSONObject("sys").getString("country");
                BigDecimal windSpeed = weatherData.getJSONObject("wind").getBigDecimal("speed");
                BigDecimal windDegree = weatherData.getJSONObject("wind").getBigDecimal("deg");


                temperatureLabel.setText(temperature.setScale(2, RoundingMode.HALF_UP) + "°C");
                maxTemperatureLabel.setText(maxTemp.setScale(2, RoundingMode.HALF_UP) + "°C");
                minTemperatureLabel.setText(minTemp.setScale(2, RoundingMode.HALF_UP) + "°C");
                humidityLabel.setText(humidity.setScale(2, RoundingMode.HALF_UP) + "%");
                descriptionLabel.setText(description);
                countryCodeLabel.setText(countryCode);
                windSpeedLabel.setText(String.valueOf(windSpeed.setScale(2, RoundingMode.HALF_UP)));
                windDegreeLabel.setText(String.valueOf(windDegree.setScale(2, RoundingMode.HALF_UP)));
            } catch (Exception e) {
                temperatureLabel.setText("Error fetching weather data");
                maxTemperatureLabel.setText("");
                minTemperatureLabel.setText("");
                humidityLabel.setText("");
                descriptionLabel.setText("");
                countryCodeLabel.setText("");
                windSpeedLabel.setText("");
                windDegreeLabel.setText("");
                System.out.println(e.getMessage());
            }
        } else {
            temperatureLabel.setText("Please enter a city name.");
            maxTemperatureLabel.setText("");
            minTemperatureLabel.setText("");
            humidityLabel.setText("");
            descriptionLabel.setText("");
            countryCodeLabel.setText("");
            windSpeedLabel.setText("");
            windDegreeLabel.setText("");
        }
    }
}
