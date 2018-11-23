import com.android.build.gradle.ProguardFiles.getDefaultProguardFile

plugins {
    id("com.android.library")
    kotlin("android")
    id("com.github.dcendents.android-maven")
}
//apply plugin: 'com.android.library'
//apply plugin: 'kotlin-android'
//apply plugin: 'com.github.dcendents.android-maven'

group = "com.github.BirjuVachhani"

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api("com.android.support:appcompat-v7:28.0.0")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.10")
}
repositories {
    mavenCentral()
}