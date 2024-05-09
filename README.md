# Apply Digital Android Test

It includes the following popular libraries:
- [Hilt](https://dagger.dev/hilt) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
- [Room](https://developer.android.com/training/data-storage/room) - Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- [Retrofit](https://github.com/square/retrofit) - A type-safe HTTP client for Android and the JVM.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
## Annotation Processing
This project uses [Kotlin Symbol Processing (KSP)](https://kotlinlang.org/docs/ksp-overview.html) for annotation processing, which provides faster build times compared to [KAPT](https://kotlinlang.org/docs/kapt.html). ~~However, some dependencies (like Hilt) may not support KSP yet, which is why KAPT is still used~~.

## Requirements 
Android Studio Jellyfish | 2023.3.1
Min SDK : 21


