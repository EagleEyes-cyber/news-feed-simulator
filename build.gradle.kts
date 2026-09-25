plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "itera.pam"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}
