# MEY APP

## Overview MEY
The MEY application is an application that can detect user expressions using Face Recognition technology, with this technology Mey can detect user expressions and connect to the list of songs we offer and the user can open the list with Youtube, Mey was created with the aim of improving the user's mood and make the user adjust the song according to the expression that is scanned

## Design APP
|Splash Screen| Home | Camera | Result Scan | List Music Based Mood
|--|--|--|--|--|
| <p align= "center"><img src="https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/Splash%20Screen.png" width="200"></p> | <p align= "center"><img src="https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/HOME.png" width="200"></p> | <p align= "center"><img src="https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/camera.png" width="200"></p> | <p align="center"><img src="https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/result%20scan.png" width="200"></p> | <p align="center"><img src="https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/List%20Music%20Based%20Mood.png" width="200"></p> 

## Feature
- **Face Recognition**
   Face Recognition is a technology that will scan human faces with cameras that use Machine Learning technology
   
- **Service For List Music**
   provide music list service after face is detected by Face Recognition
   
## Libary Used
- [Coil](https://coil-kt.github.io/coil/) -> **For fect image from internet**
- [Tensor Flow](https://www.tensorflow.org/lite/inference_with_metadata/lite_support) -> **For model machine learning to android**
- [Retrofit](https://square.github.io/retrofit/) -> **For request API**
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore?hl=id) -> **For local saved in android**
- [Shimmer](https://facebook.github.io/shimmer-android/) -> **For loading Cardview in android**
- [Karumi Dexter](https://github.com/Karumi/Dexter) -> **For permission in android**
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) -> **For observer live data**
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=id) -> **For inject to constructor in viewmodel**
- [Firebase Auth](https://firebase.google.com/products/auth?gclsrc=aw.ds&gclid=CjwKCAjw14uVBhBEEiwAaufYx_byR8Qg-gpiqSa2sBK6Kh04k0UaCVVXoyVaw9AAQYgXD-0NtY6FLBoCrwwQAvD_BwE) -> **For authentication for user**
- [Firebase database](https://firebase.google.com/products/realtime-database?gclsrc=aw.ds&gclid=CjwKCAjw14uVBhBEEiwAaufYx6T0mwE4qaer1nVB4A20GkmtkOnEz3aQCkRLorW1z9fyI1mSEFCLHxoCeSMQAvD_BwE) -> **For database all data user in android**
- [ViewPager](https://developer.android.com/training/animation/screen-slide) -> **For slider in OnBoarding**

## Architecture
|Architecture MVVM | Overview Architecture |
|--|--|
|<p align="center"><img src="https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/mvvm.png" width="350"></p> | Model–view–viewmodel (MVVM) is a software architectural pattern that facilitates the separation of the development of the graphical user interface (the view) – be it via a markup language or GUI code – from the development of the business logic or back-end logic (the model) so that the view is not dependent on any specific model platform.


## Download App
You can download app Mey [versi 1.0.0](https://github.com/MEY-Mental-Education-Yes/MEY_APP/releases/tag/1.0.0)

