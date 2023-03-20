import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("multiplatform") apply false
    kotlin("jvm") apply false
}
val JVM_TAEGET = "11"

group = "ru.rifnur.tasktracker"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JVM_TAEGET
    }
    tasks.withType<KotlinJvmCompile> {
        kotlinOptions.jvmTarget = JVM_TAEGET
    }

}