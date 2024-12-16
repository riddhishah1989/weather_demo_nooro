#  Weather Tracker App
A weather tracking application built with Kotlin and Jetpack Compose. It allows users to search for cities, view detailed weather information, and save the selected city for future use.

#  Features
  
###  Home Screen:

Displays weather details for a saved city (Temperature, Weather Condition, Humidity, UV Index, Feels Like Temperature).

Prompts the user to search for a city if no city is saved.

###  Search Functionality:

Search bar to find weather details for a city.

Saves the selected city and updates the Home Screen.

###  Local Storage:
Uses Shared Preferences  to persist the selected city across app launches.

## Technology Stack

Language: Kotlin

UI Framework: Jetpack Compose

Architecture: MVVM (Model-View-ViewModel)

Networking: Retrofit with WeatherAPI.com

Local Storage: Shared Preferences

Dependency Injection: Hilt

### Setup Instructions

# Prerequisites
Android Studio Arctic Fox (or higher)

Minimum SDK: 26

Compile SDK: 35

WeatherAPI Key (Sign up for free at WeatherAPI)

## Steps to Run the App

Clone this repository: git clone https://github.com/riddhishah1989/weather_demo_nooro.git

Open the project in Android Studio.

# WeatherAPI key in the ApiConstants file:

object ApiConstants 
{

    const val WEATHER_API_KEY = "YOUR_API_KEY"

}

Build and run the app on an emulator or physical device.

### Architecture Overview

The app follows Clean Architecture principles to ensure scalability, testability, and separation of concerns:

# UI Layer:

Built using Jetpack Compose for declarative UI.

Includes a Home Screen and a Search Bar.

# ViewModel:

Manages the app state and business logic.

Handles API calls, local data persistence, and UI state updates.

# Repository:

Mediates between the data sources (Remote API and Local Storage).

Abstracts data-fetching logic to maintain clean separation.

# Data Layer:

Remote data from WeatherAPI.com.

Local data is stored using Shared Preferences for persisting selected city.

# API Integration
The app integrates with WeatherAPI.com to fetch real-time weather data. 

# Key endpoints:

GET /current.json:

Retrieves weather details for a city.
