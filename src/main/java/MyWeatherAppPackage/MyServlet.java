//package MyWeatherAppPackage;
//
//
//import jakarta.servlet.ServletException;
//
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Scanner;
//import java.util.Date;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
///**
// * Servlet implementation class MyServlet
// */
//public class MyServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public MyServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String inputData = request.getParameter("userInput");
//		
//		//API Setup
//		String apiKey = "549625de4f4eaf22c4d18d9df699ccfb";
//		
//		// Get the city from the form input
//		String city = request.getParameter("city");
//		
//		//Create the URL for the OpenWeatherMap API request
//		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid="+ apiKey;
//		
//		
//		//API Integration
//		URL url = new URL(apiUrl);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestMethod("GET");
//		
//		
//		// Reading the data from network
//		InputStream inputStream = connection.getInputStream();
//		InputStreamReader reader = new InputStreamReader(inputStream);
//		
//		// Want to store in string
//		
//		StringBuilder responseContent = new StringBuilder();
//		
//		// Input lene ke liye from the reader, will create scanner object.
//		Scanner scanner = new Scanner(reader);
//		
//		while(scanner.hasNext()) {
//			responseContent.append(scanner.nextLine());
//		}
//		
//		scanner.close();
//
//	
//		// TypeCasting  = Parsing the data into JSON 
//		Gson gson = new Gson();
//		JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
////		System.out.println(jsonObject);
//		
//		 //Date & Time
//        long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
//        String date = new Date(dateTimestamp).toString();
//        
//        //Temperature
//        double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
//        int temperatureCelsius = (int) (temperatureKelvin - 273.15);
//       
//        //Humidity
//        int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
//        
//        //Wind Speed
//        double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
//        
//        //Weather Condition
//        String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
//		
//		
//        // Set the data as request attributes (for sending to the jsp page)
//        request.setAttribute("date", date);
//        request.setAttribute("city", city);
//        request.setAttribute("temperature", temperatureCelsius);
//        request.setAttribute("weatherCondition", weatherCondition);
//        request.setAttribute("humidity", humidity);
//        request.setAttribute("windSpeed", windSpeed);
//        request.setAttribute("weatherData", responseContent.toString());
//        
//        connection.disconnect();
//        
//	
//    // Forward the request to the weather.jsp page for rendering
//        request.getRequestDispatcher("index.jsp").forward(request, response);
//        
//   }
//	
//}





//package MyWeatherAppPackage;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URLEncoder;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
///**
// * Servlet implementation class MyServlet
// */
//public class MyServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public MyServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//    /**
//     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//     */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        response.getWriter().append("Served at: ").append(request.getContextPath());
//    }
//
//    /**
//     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Get the city from the form input
//        String city = request.getParameter("city");
//
//        if (city == null || city.trim().isEmpty()) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "City parameter is missing");
//            return;
//        }
//
//        // URL encode the city parameter
//        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
//
//        // API Setup
//        String apiKey = "761c18b3b53064d2c31803a86fd07188";
//
//        // Create the URL for the OpenWeatherMap API request
//        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCity + "&appid=" + apiKey;
//
//        // API Integration
//        URL url = new URL(apiUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        // Check the response code before reading the InputStream
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            // Reading the data from network
//            InputStream inputStream = connection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream);
//            StringBuilder responseContent = new StringBuilder();
//
//            try (Scanner scanner = new Scanner(reader)) {
//                while (scanner.hasNext()) {
//                    responseContent.append(scanner.nextLine());
//                }
//            }
//
//            // Parse JSON response
//            Gson gson = new Gson();
//            JsonObject jsonResponse = gson.fromJson(responseContent.toString(), JsonObject.class);
//
//            System.out.println(jsonResponse);
//
//            // Send the response content back to the client
//            response.setContentType("application/json");
//            response.getWriter().write(jsonResponse.toString());
//        } else {
//            // Handle the error response
//            InputStream errorStream = connection.getErrorStream();
//            StringBuilder errorContent = new StringBuilder();
//            
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(errorStream))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    errorContent.append(line);
//                }
//            }
//
//            System.out.println("Error: " + responseCode + " - " + errorContent);
//            response.sendError(responseCode, "Failed to fetch weather data: " + errorContent.toString());
//        }
//    }
//}















// Correct Code :-
package MyWeatherAppPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the city from the form input
        String city = request.getParameter("city");

        if (city == null || city.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "City parameter is missing");
            return;
        }

        // URL encode the city parameter
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());

        // API Setup
        String apiKey = "549625de4f4eaf22c4d18d9df699ccfb";

        // Create the URL for the OpenWeatherMap API request
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCity + "&appid=" + apiKey;

        // API Integration
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Check the response code before reading the InputStream
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Reading the data from network
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            StringBuilder responseContent = new StringBuilder();

            try (Scanner scanner = new Scanner(reader)) {
                while (scanner.hasNext()) {
                    responseContent.append(scanner.nextLine());
                }
            }

            // Parse JSON response
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);

            // Date & Time
            long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
            String date = new Date(dateTimestamp).toString();

            // Temperature
            double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
            int temperatureCelsius = (int) (temperatureKelvin - 273.15);

            // Humidity
            int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();

            // Wind Speed
            double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();

            // Weather Condition
            String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();

            // Set the data as request attributes (for sending to the jsp page)
            request.setAttribute("date", date);
            request.setAttribute("city", city);
            request.setAttribute("temperature", temperatureCelsius);
            request.setAttribute("weatherCondition", weatherCondition);
            request.setAttribute("humidity", humidity);
            request.setAttribute("windSpeed", windSpeed);
            request.setAttribute("weatherData", responseContent.toString());

            connection.disconnect();

            // Forward the request to the weather.jsp page for rendering
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            // Handle the error response
            InputStream errorStream = connection.getErrorStream();
            StringBuilder errorContent = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(errorStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    errorContent.append(line);
                }
            }

            System.out.println("Error: " + responseCode + " - " + errorContent);
            response.sendError(responseCode, "Failed to fetch weather data: " + errorContent.toString());
        }
    }
}

