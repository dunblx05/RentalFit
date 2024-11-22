plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ssafy.rentalfit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ssafy.rentalfit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    //indicator
    implementation ("me.relex:circleindicator:2.1.6")

    // FCM 사용 위한 plugins
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation ("com.google.firebase:firebase-messaging-ktx")

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx:23.1.0")
    implementation("com.google.android.gms:play-services-auth:20.4.0")

    // https://github.com/square/retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // https://github.com/square/okhttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

    // https://github.com/square/retrofit/tree/master/retrofit-converters/gson
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Glide 사용
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    //Splash Screen
    implementation ("androidx.core:core-splashscreen:1.0.1")

    //framework ktx dependency 추가
    implementation ("androidx.fragment:fragment-ktx:1.6.2")
}