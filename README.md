NetworkAPI
==========
This project contains a basic [NetworkService](https://github.com/saquibhafiz/NetworkAPI/blob/master/src/com/example/networkrequestsapi/NetworkService.java) that takes care of all the network calls an Android app has to make. The purpose of this is for developers to use this service and make calls instantly without having to build an entire backend for the application. This Service can be put into any project and used to interact with any server API. This cuts developer time by hours in the initial creation and testing process of an Android application. It comes with debug mode so you can track it with Logcat.

To use this project successfully just copy the NetworkService.java file into your project and you’re done. Bind to it like another service and made all the basic HTTP requests. Android developers code and recode these basic modules constantly and uniquely for each project. I abstracted them into their base concept for better use.

Advantages:
- easy and understandable
- manages calls so you don't make dozens of calls at once crashing your app
- cuts development time