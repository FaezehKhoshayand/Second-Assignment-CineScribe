# Project Title

* CineScribe

## Description

* This application acts as a simple movie aggregator, fetching data from both the IMDB and Actor API to provide information about movies and  actors/actresses.

## How it works

* The user enters the desired option it can be Actor or Movie and the program returns the information related to it(NetWorth,Nationality,Date of release,..) which is received from the API.I wrote some functions that use JSON and return variables. If the API returns an error or the movie/actor name is not found,it prompts the user to type again. That's all this program does. 

## The challenges I encountered

* Since this was my first time working with Gradle I wasn't sure if it was working correctly and when I got an error I thought it's caused by the gradle but then I realized that I hadn't noticed the brackets in one of the Jsons so I changed every single function that I had written using JSONArray.The next problem was printing arrays with no brackets as a result I changed the return type of functions to strings.I also had to learn how to work with JSON and its functions.

### Dependencies

* ex. Windows 10
* gradle 

## Authors

* Faezeh Khoshayand

## Version History
* 0.6
    * Error handling
* 0.5
    * Various bug fixes and optimizations in Main and Movie
* 0.4
    * Main was made
* 0.3
    * Various bug fixes and optimizations
* 0.2
    * Various bug fixes and a new Actors constructor
* 0.1
    * Initial Release (All test cases passd)

