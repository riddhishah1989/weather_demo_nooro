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
