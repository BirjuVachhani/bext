// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.gradle.script.lang.kotlin.*
import org.gradle.api.plugins.*

buildscript {
    val kotlinVersion = "1.3.0"
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1") // For jit-pack

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

/*task clean(type: Delete) {
    delete rootProject.buildDir
}*/
