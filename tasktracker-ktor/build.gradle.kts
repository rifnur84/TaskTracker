import io.ktor.plugin.features.*
import org.jetbrains.kotlin.util.suffixIfNot
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

val ktorVersion: String by project
val logbackVersion: String by project
val serializationVersion: String by project
val javaVersion: String by project

fun ktorServer(module: String, version: String? = this@Build_gradle.ktorVersion): Any =
    "io.ktor:ktor-server-$module:$version"
fun ktorClient(module: String, version: String? = this@Build_gradle.ktorVersion): Any =
    "io.ktor:ktor-client-$module:$version"

plugins {
    kotlin("multiplatform")
    id("io.ktor.plugin")
    id("application")
    id("com.bmuschko.docker-java-application")
    id("com.bmuschko.docker-remote-api")
    kotlin("plugin.serialization")
}

val webjars: Configuration by configurations.creating
dependencies {
    val swaggerUiVersion: String by project
    webjars("org.webjars:swagger-ui:$swaggerUiVersion")
}

application {
    mainClass.set("io.ktor.servercio.cio.EngineMain")
}

//ktor {
//    docker {
//        localImageName.set(project.name)
//        imageTag.set(project.version.toString())
//        jreVersion.set(JreVersion.valueOf("JRE_$javaVersion"))
//    }
//}

kotlin {
    jvm {withJava()}

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
                    entryPoint = "ru.rifnur.tasktracker.app.main"
                }
            }
        }

    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(ktorServer("core")) // "io.ktor:ktor-server-core:$ktorVersion"
                implementation(project(":tasktracker-common"))
                implementation(project(":tasktracker-mappers"))
                implementation(project(":tasktracker-stubs"))
                implementation(project(":tasktracker-api-log"))
                implementation(project(":api-v2-kmp"))
                implementation(project(":tasktracker-biz"))
                implementation(project(":tasktracker-logging-kermit"))
                implementation(project(":tasktracker-mappers-log"))
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

                implementation(ktorServer("core")) // "io.ktor:ktor-server-core:$ktorVersion"
                implementation(ktorServer("cio")) // "io.ktor:ktor-server-cio:$ktorVersion"
                implementation(ktorServer("auth")) // "io.ktor:ktor-server-auth:$ktorVersion"
                implementation(ktorServer("auto-head-response")) // "io.ktor:ktor-server-auto-head-response:$ktorVersion"
                implementation(ktorServer("caching-headers")) // "io.ktor:ktor-server-caching-headers:$ktorVersion"
                implementation(ktorServer("cors")) // "io.ktor:ktor-server-cors:$ktorVersion"
                implementation(ktorServer("websockets")) // "io.ktor:ktor-server-websockets:$ktorVersion"
                implementation(ktorServer("config-yaml")) // "io.ktor:ktor-server-config-yaml:$ktorVersion"
                implementation(ktorServer("content-negotiation")) // "io.ktor:ktor-server-content-negotiation:$ktorVersion"
                implementation(ktorServer("websockets")) // "io.ktor:ktor-websockets:$ktorVersion"
                implementation(ktorServer("auth"))

                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

            }
        }
        @Suppress("UNUSED_VARIABLE")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                implementation(ktorServer("test-host")) // "io.ktor:ktor-server-test-host:$ktorVersion"
                implementation(ktorClient("content-negotiation"))

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
                implementation(ktorServer("test-host")) // "io.ktor:ktor-server-test-host:$ktorVersion"
                implementation(ktorClient("content-negotiation"))
                implementation(ktorClient("websockets"))
                implementation("io.ktor:ktor-server-test-host:$ktorVersion")
                implementation("org.jetbrains.kotlin:kotlin-test:1.8.0")
             }
         }

        @Suppress("UNUSED_VARIABLE")
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))

                implementation(ktorServer("netty")) // "io.ktor:ktor-ktor-server-netty:$ktorVersion"

                // jackson
                implementation("io.ktor:ktor-serialization-jackson:$ktorVersion")

//                implementation(ktorServer("locations"))
                implementation(ktorServer("caching-headers"))
                implementation(ktorServer("call-logging"))
                implementation(ktorServer("auto-head-response"))
                implementation(ktorServer("default-headers")) // "io.ktor:ktor-cors:$ktorVersion"
                implementation(ktorServer("auto-head-response"))

                implementation(ktorServer("auth-jwt")) // "io.ktor:ktor-auth-jwt:$ktorVersion"

//                implementation("ch.qos.logback:logback-classic:$logbackVersion")
//                implementation(project(":ok-marketplace-lib-logging-logback"))

                // transport models
                implementation(project(":tasktracker-common"))
                implementation(project(":api-v2-kmp"))
                implementation(project(":tasktracker-mappers"))
                implementation(project(":tasktracker-lib-logging-logback"))

                // Stubs
                implementation(project(":tasktracker-stubs"))

                implementation("com.sndyuk:logback-more-appenders:1.8.8")
                implementation("org.fluentd:fluent-logger:0.3.4")
            }
        }
        @Suppress("UNUSED_VARIABLE")
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}
tasks {
    @Suppress("UnstableApiUsage")
    withType<ProcessResources>().configureEach {
        println("RESOURCES: ${this.name} ${this::class}")
        from("$rootDir/specs") {
            into("specs")
            filter {
                // Устанавливаем версию в сваггере
                it.replace("\${VERSION_APP}", project.version.toString())
            }
        }
        webjars.forEach { jar ->
//        emptyList<File>().forEach { jar ->
            val conf = webjars.resolvedConfiguration
            println("JarAbsPa: ${jar.absolutePath}")
            val artifact = conf.resolvedArtifacts.find { it.file.toString() == jar.absolutePath } ?: return@forEach
            val upStreamVersion = artifact.moduleVersion.id.version.replace("(-[\\d.-]+)", "")
            copy {
                from(zipTree(jar))
                duplicatesStrategy = DuplicatesStrategy.EXCLUDE
                into(file("${buildDir}/webjars-content/${artifact.name}"))
            }
            with(this@configureEach) {
                this.duplicatesStrategy = DuplicatesStrategy.EXCLUDE
                from(
                    "${buildDir}/webjars-content/${artifact.name}/META-INF/resources/webjars/${artifact.name}/${upStreamVersion}"
                ) { into(artifact.name) }
                from(
                    "${buildDir}/webjars-content/${artifact.name}/META-INF/resources/webjars/${artifact.name}/${artifact.moduleVersion.id.version}"
                ) { into(artifact.name) }
            }
        }
    }
}

//tasks {
//    val dockerJvmDockerfile by creating(Dockerfile::class) {
//        group = "docker"
//        from("openjdk:17")
//        copyFile("app.jar", "app.jar")
//        entryPoint("java", "-Xms256m", "-Xmx512m", "-jar", "/app.jar")
//    }
//    create("dockerBuildJvmImage", DockerBuildImage::class) {
//        group = "docker"
//        dependsOn(dockerJvmDockerfile, named("jvmJar"))
//        doFirst {
//            copy {
//                from(named("jvmJar"))
//                into("${project.buildDir}/docker/app.jar")
//            }
//        }
//        images.add("${project.name}:${project.version}")
//    }
//}

tasks {
    val linkReleaseExecutableNative by getting(KotlinNativeLink::class)

    val dockerDockerfile by creating(Dockerfile::class) {
        group = "docker"
        from("openjdk:17")
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
