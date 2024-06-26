plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.viewnext"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.viewnext"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    implementation("com.google.firebase:firebase-config-ktx:21.6.3")
    implementation (libs.retrofit)
    implementation (libs.material.v140)
    kapt (libs.androidx.room.compiler)
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("androidx.activity:activity-ktx:1.3.1")
    implementation (libs.retrofit2.retrofit.mock)
    implementation("com.google.android.gms:play-services-auth:21.1.0")
    implementation (libs.infinum.retromock)
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx")
    testImplementation("org.mockito:mockito-core:3.12.4")
    implementation ("io.ktor:ktor-client-core:2.0.0")
    implementation (libs.ktor.client.cio)
    implementation ("io.ktor:ktor-client-serialization:2.0.0")
    implementation ("io.ktor:ktor-client-content-negotiation:2.0.0")
    implementation ("io.ktor:ktor-client-logging:2.0.0")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.android.v160)

    // Hilt dependencies

    implementation (libs.hilt.android)

    kapt (libs.hilt.android.compiler)
}
