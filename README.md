# News App

A simple news app that allows users to get headlines based on their preferences and search articles.

## Technology Stack


- Code Architecture (MVI)
- Jetpack Compose
- Clean Architecture
- Modularization
- Flow & StateFlow to give view the data and notify it when a change occurs.
- Dagger-hilt to handle dependency injection.
- Co-routines to deal with threads.
- Retrofit2 & OkHttp3 to handle apis requests.
- Room DataBase to cache the data and display it in case there is no internet
- Coil to load images from internet.

## Features

### Onboarding

On the first launch, users choose a favorite country and 3 categories to personalize their experience. This data is stored locally for future sessions.

### Headlines Feed 

The headlines screen fetches and displays the latest articles from the user's chosen country and categories. Articles are sorted by date and tapping opens the URL in a web browser.

### Search

Users can search for articles across all categories or filter results. Search results also open externally for a better reading experience. Saved articles are stored locally.  

### Data Storage

Room is used to cache API responses and save favorite articles for offline access. This improves performance and user experience when connectivity is limited.

### Future Enhancements

- Arabic localization to support additional languages/regions
- Offline caching of images for improved offline mode
- In-app article viewing instead of external browser

### Screen Shots

<table>
  <tr>
    <td><img src="https://github.com/ahmedelbagory332/News-App/blob/master/screenShots/1.png" width=270 height=480></td>
    <td><img src="https://github.com/ahmedelbagory332/News-App/blob/master/screenShots/2.png" width=270 height=480></td>
    <td><img src="https://github.com/ahmedelbagory332/News-App/blob/master/screenShots/3.png" width=270 height=480></td>
    <td><img src="https://github.com/ahmedelbagory332/News-App/blob/master/screenShots/4.png" width=270 height=480></td>
  </tr>
 </table>
 <table>
  <tr>
    <td><img src="https://github.com/ahmedelbagory332/News-App/blob/master/screenShots/5.png" width=270 height=480></td>
    <td><img src="https://github.com/ahmedelbagory332/News-App/blob/master/screenShots/6.png" width=270 height=480></td>
    <td><img src="https://github.com/ahmedelbagory332/News-App/blob/master/screenShots/7.png" width=270 height=480></td>
    <td><img src="https://github.com/ahmedelbagory332/News-App/blob/master/screenShots/8.png" width=270 height=480></td>
  </tr>
 </table>
 


