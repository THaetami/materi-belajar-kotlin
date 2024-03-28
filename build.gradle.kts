plugins {
    kotlin("jvm") version "1.9.22"
}

group = "materi-cotlin-pemula"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.31")
    // kotlin coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}