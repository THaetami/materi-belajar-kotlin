plugins {
    kotlin("jvm") version "1.9.22"
}

group = "materi-cotlin-pemula"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.31")
    // kotlin coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")

    // untuk proses mocking test
    implementation("org.mockito:mockito-junit-jupiter:5.10.0")

    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation("org.junit.jupiter:junit-jupiter-params:5.1.0")

    // Use the JUnit 5 integration.
//    testImplementation(libs.junit.jupiter.engine)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform{
        excludeTags("integration-test") // ketika run `gradle test`, test dengan @Tag("integration-test") akan dikecualikan
    }
}

// run `gradle integration-test` maka test yang menggunakan @Tag("integration-test") akan dijalankan | cek file SampleIntegratinTest
tasks.register("integration-test", Test::class) {
    useJUnitPlatform{
        includeTags("integration-test")
    }
}

kotlin {
    jvmToolchain(21)
}

