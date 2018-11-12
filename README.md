# Android Voice Assistant

```
For security reasons, the Firebase settings file has not been upload. Therefore the app won't work properly
```

This project is a Voice Assistant for Android. Using voice command it is possible to execute simple task like open any installed app in the phone.
The Voice Recognizer use Google's Android native SpeechRecognizer.
Currently the SpeechRecognizer works in English and Spanish.

The main functions are:

- Load News Feed
- Realtime Database (Firebase)
- Open Apps

The app is writen in Kotlin and the Architecture pattern is MVP (Model-View-Presenter)

## App Functions
The SpeechRecognicer works throw a simple commands.
The command structure is as follows:

```
{Key word} {command} {command argument}
```

Example

```
voice new message This is a test
```
- voice: Key word that indicates the speech input is a command to be executed.
- new message: command for create a new message in the Firebase Realtime Database.
- This is a test: command argument, text to the written in the Database.

### News Feed
Command:

```
voice get news feed
```

API:

```
https://newsapi.org/s/google-news-api
```

Using REST Service it gets the Top Headlines at the current moment

### Realtime Database
Command new message:

```
voice new message This is a test
```

Command load all message:

```
voice get message
```

Using Google's Firebase Realtime DataBase, the app is able to create new message and load all the current message.

### Open Apps
Command:

```
voice open Facebook
```

Open any installed app in the phone.

### ProcessSpeech
The ProcessSpeech use Regex String for find matching patterns with the availabe commands.
```
/^voice\s(open|get news feed|get message)/
```

This Regex String is formed for 2 groups:
- First Group: `voice`
  Checks if the first word is the Key word
- Second Group: `(open|get news feed|get message)`
  All the availabe commands, usings OR filter checks is the words after the Key word are a valid command

If the Speech input is a valid command, all the words after the Second Group are the Command Argument.
The Command Argument is used for the content of a new message or the name of the app to be opened.

## Architecture

### MVP
The View is formed by the MainActivity and 3 Fragments: NewsFeedFragment, MessageFragment and AppFragment.
From the MainActivity the SpeechRecognizer is executed. And the ModelSpeech returns and object that contains the speech input and which order is going to be executed.

```kotlin
data class SpeechResult(
        var message: String, // Speech input full text
        var isKeyWord: Boolean, // If Speech input is an order
        var command: String, // Order 
        var commandTy: Tools.CommandTy, // CommandTy from command
        var commandArgument: String, // Name of app to open, information to seach in internet
        var languageCode: Tools.LanguageCode // Language Code
)
```

MainViewPresenter based on the SpeechRecognizer Result decides which fragment is going to launch and returns it to the MainActivity throw a callback.

Once the fragment is initiated, it executes the selected function. The 3 Fragments share the same Presenter(FragmentPresenter) throw a generic callback.

### Factory
The ProcessSpeech calls the CommandFactory for get a map of the available commands for compare it with the speech input. 

```kotlin
class CommandFactory {
    companion object {
        fun createCommandMap(languageCode: Tools.LanguageCode): ICommandFactory
            = when(languageCode){
                .......
            }
    }
}
```

### Singleton
NewsApiClient, FirebaseClient and PackageManagerClient are Singleton objects

```kotlin
object ApiClient {
    .......
}
```

```kotlin
object FirebaseClient  {
    .......
}
```

```kotlin
object PackageManagerClient  {
    .......
}
```

# Changelog
V2.0
  - Singleton and Factory added
  - View divided in Fragment 
  - Realtime Database (Firebase)
  - News Feed
  - Logic Fix
  
V 1.0
  - Update architecture to MVP Pattern
  - Rewrite speech process logic with Regex

V 0.5
  - Speech Recognition
  - Launch apps
