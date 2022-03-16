# Overview

This app displays Shakespeare's Sonnets. On the main screen, the user can select a number using a number picker and use a select button to select that sonnet number. The app queries a Firestore NoSQL database for the text of that sonnet and passes the text to a new activity which is used to display the sonnet.

This is a demonstration project showing the development of a basic Android app. My goal with this project was to become familiar with the basics of building an Android app and designing app layouts.

[Software Demo Video](https://youtu.be/c3DjCTSqPiA)

# Development Environment

* Android Studio Bumblebee 2021.1.1 Patch 2
* Gradle 7.2
* JDK 11.0.11
    * Google Services 4.3.10
    * Firebase-Firestore 24.0.2
    * Firebase-Auth 21.0.2

# Useful Websites

* [Android First App tutorial](https://developer.android.com/training/basics/firstapp)
* [Google Cloud Firestore Documentation](https://cloud.google.com/firestore/docs)
* [Google Cloud Firestore Java API](https://googleapis.dev/java/google-cloud-firestore/latest/overview-summary.html)

# Future Work

* Add random sonnet selection to the app
* Add in audio option so that the sonnets can be listened to as well as read
* Add more content to Firestore database that can be displayed in the app (famous lines from Shakespeare's plays, poems by other authors, etc)
* Add additional menu screen for selecting different types of content to display from Firestore (sonnets, poems, quotes, etc)
