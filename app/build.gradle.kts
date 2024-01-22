plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.ricelimepredictionapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ricelimepredictionapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        mlModelBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("org.tensorflow:tensorflow-lite-metadata:0.1.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.camera:camera-view:1.3.1")
    implementation("androidx.media3:media3-common:1.2.0")
//    implementation("org.tensorflow:tensorflow-lite-support:0.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.exyte:animated-navigation-bar:1.0.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
//    implementation("org.tensorflow:tensorflow-lite-task-vision:0.4.0")
    implementation("io.coil-kt:coil-compose:2.2.0")
    implementation("androidx.camera:camera-camera2:1.4.0-alpha03")
    implementation("androidx.camera:camera-lifecycle:1.4.0-alpha03")
//    implementation("org.tensorflow:tensorflow-lite-support:2.8.0")
    implementation("org.tensorflow:tensorflow-lite-gpu:2.13.0")
    implementation("org.tensorflow:tensorflow-lite-task-vision:0.4.4")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    // Room components
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")

    implementation("androidx.paging:paging-compose:3.3.0-alpha02")

    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")


    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
}