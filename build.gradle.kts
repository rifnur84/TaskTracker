import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") apply false
}

group = "ru.rifnur.tasktracker"
version = "1.0-SNAPSHOT"

subprojects {
    group = rootProject.group
    version = rootProject.version
    repositories {
        mavenCentral()
    }


    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

}