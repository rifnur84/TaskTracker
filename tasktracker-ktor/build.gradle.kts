import org.jetbrains.kotlin.util.suffixIfNot
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

val ktorVersion: String by project
val logbackVersion: String by project
val serializationVersion: String by project

// ex: Converts to "io.ktor:ktor-ktor-server-netty:2.0.1" with only ktor("netty")
fun ktor(module: String, prefix: String = "server-", version: String? = this@Build_gradle.ktorVersion): Any =
    "io.ktor:ktor-${prefix.suffixIfNot("-")}$module:$version"

plugins {
    id("application")
    id("com.bmuschko.docker-java-application")
    id("com.bmuschko.docker-remote-api")
    kotlin("plugin.serialization")
    kotlin("multiplatform")
}

repositories {
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
//    mainClass.set("ru.rifnur.tasktracker.ApplicationKt")
}

kotlin {
    jvm {}

        val nativeTarget = when (System.getProperty("os.name")) {
            "Mac OS X" -> macosX64("native")
            "Linux" -> linuxX64("native")
            // Windows is currently not supported
            // Other supported targets are listed here: https://ktor.io/docs/native-server.html#targets
            else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
        }
        nativeTarget.apply {
            binaries {
                executable {
                    entryPoint = "ru.rifnur.tasktracker.main"
                }
            }
        }

    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(ktor("core")) // "io.ktor:ktor-server-core:$ktorVersion"
                implementation(project(":tasktracker-common"))
                implementation(project(":tasktracker-mappers"))
                implementation(project(":tasktracker-stubs"))
                implementation(project(":api-v2-kmp"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
            }
        }
        @Suppress("UNUSED_VARIABLE")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
         @Suppress("UNUSED_VARIABLE")
        val nativeMain by getting {
             dependencies {
                 implementation("io.ktor:ktor-server-core:$ktorVersion")
                 implementation("io.ktor:ktor-server-cio:$ktorVersion")
                 implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
                 implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                 implementation("io.ktor:ktor-server-core:$ktorVersion")
                 implementation("io.ktor:ktor-server-cio:$ktorVersion")
             }
         }
         @Suppress("UNUSED_VARIABLE")
         val nativeTest by getting {
             dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation(ktor("test-host")) // "io.ktor:ktor-server-test-host:$ktorVersion"
                implementation(ktor("content-negotiation", prefix = "client-"))
                implementation("io.ktor:ktor-server-test-host:$ktorVersion")
                implementation("org.jetbrains.kotlin:kotlin-test:1.8.0")
             }
         }

    }
}

tasks {
    val linkReleaseExecutableNative by getting(KotlinNativeLink::class)

    val dockerDockerfile by creating(Dockerfile::class) {
        group = "docker"
        from("ubuntu:22.02")
        doFirst {
            copy {
                from(linkReleaseExecutableNative.binary.outputFile)
                into("${this@creating.temporaryDir}/app")
            }
        }
        copyFile("app", "/app")
        entryPoint("/app")
    }
    create("dockerBuildNativeImage", DockerBuildImage::class) {
        group = "docker"
        dependsOn(dockerDockerfile)
        images.add("${project.name}:${project.version}")
    }
}
