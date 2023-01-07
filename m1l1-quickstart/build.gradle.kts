import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}

dependencies{
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test-junit"))
}