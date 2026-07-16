# 🎬 Oggy - Legal Streaming Application

A modern Android streaming application that fetches movies and TV shows from legal APIs with automatic updates, beautiful UI, and user-friendly features.

## 📋 Features

- **Legal Content**: Uses TMDb API for movie/TV show data
- **Auto-Update**: Automatically fetches new content periodically
- **Search & Filter**: Find content by genre, rating, release date
- **Watchlist**: Save favorite movies/shows
- **Beautiful UI**: Modern Material Design with custom logo
- **Thumbnails**: High-quality poster images
- **Recommendations**: Smart content suggestions based on user preferences
- **Offline Support**: Cache popular content

## 🛠️ Tech Stack

- **Language**: Kotlin
- **IDE**: Android Studio
- **Architecture**: MVVM with Clean Architecture
- **Database**: Room Database
- **Networking**: Retrofit + OkHttp
- **UI Framework**: Jetpack Compose / Material Design
- **APIs**: TMDb (The Movie Database)

## 📦 Project Structure

```
oggy-streaming-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/oggy/
│   │   │   │   ├── ui/
│   │   │   │   │   ├── screens/
│   │   │   │   │   ├── components/
│   │   │   │   │   └── theme/
│   │   │   │   ├── viewmodel/
│   │   │   │   ├── repository/
│   │   │   │   ├── network/
│   │   │   │   ├── database/
│   │   │   │   ├── model/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── values/
│   │   │   │   └── layout/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## 🚀 Getting Started

### Prerequisites
1. Android Studio installed (latest version)
2. JDK 11 or higher
3. TMDb API Key (free from https://www.themoviedb.org/settings/api)

### Setup Steps

1. **Clone/Create Project**
   ```bash
   git clone <repository-url>
   cd oggy-streaming-app
   ```

2. **Add TMDb API Key**
   - Create `local.properties` file in project root
   - Add: `TMDB_API_KEY=your_api_key_here`

3. **Build & Run**
   ```bash
   ./gradlew build
   ./gradlew installDebug
   ```

## 📱 App Screens

1. **Home Screen**: Featured movies, trending content
2. **Search Screen**: Browse and filter movies/shows
3. **Details Screen**: Full movie information, reviews, trailers
4. **Watchlist Screen**: User's saved movies/shows
5. **Settings Screen**: Preferences, cache management

## 🔄 Auto-Update Mechanism

- WorkManager scheduled task runs every 24 hours
- Fetches latest movies, trending content
- Updates local database with new content
- Notifies user of new releases

## 📄 License

This project uses legal APIs and respects content ownership.
- TMDb API: https://www.themoviedb.org/
- Content licenses as per TMDb terms

## 👨‍💻 Development Team

Created by: aki-oggy

---

**Note**: This application is built with legal APIs and respects all intellectual property rights.
