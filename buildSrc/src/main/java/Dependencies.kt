import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.example.mytranslater"
    const val build_tool_version = "30.0.3"
    const val compile_sdk = 30
    const val target_sdk = 30
    const val min_sdk = 22
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val impl = ":impl"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
}

object Versions {
    //Kotlin
    const val kotlin_version = "1.4.30"
    const val lifecycle = "2.3.0"
    const val core = "1.3.2"
    const val coroutines = "1.4.1"
    const val retrofit_coroutines = "0.9.2"

    //Glide
    const val glide = "4.11.0"
    const val glide_compiler = "4.9.0"

    //Room
    const val room = "2.2.0-alpha01"

    //Design
    const val appcompat = "1.2.0"
    const val material = "1.3.0"
    const val constaintlayout = "2.0.4"
    const val legacy_support_v4 = "1.0.0"

    //Dagger
    const val dagger = "2.28.3"

    // Retrofit 2
    const val retrofit = "2.9.0"
    const val converter_gson = "2.7.1"
    const val interceptor = "3.12.1"

    //TEST
    const val junit = "4.13.2"
    const val android_x_junit = "1.1.2"
    const val espresso = "3.3.0"
}

object Kotlin {
    //Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
}

object Coroutines {
    //Coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutines_adapter_retrofit =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit_coroutines}"
}

object Glide {
    //Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler}"
}

object Room {
    //Room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Desing {
    //Design
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.appcompat}"
    const val constaintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constaintlayout}"
    const val legacy_support_v4 = "androidx.legacy:legacy-support-v4:${Versions.legacy_support_v4}"
}

object Dagger {
    //Dagger
    const val dagger = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Retrofit {
    // Retrofit 2
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converter_gson}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object TestImpl {
    //TEST
    const val junit = "junit:junit:${Versions.junit}"
    const val android_x_junit = "androidx.test.ext:junit:${Versions.android_x_junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}