buildscript {
    dependencies {
        //Ktor
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.5.21")
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("org.jetbrains.kotlin.kapt") version "2.0.0-Beta1" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false

}