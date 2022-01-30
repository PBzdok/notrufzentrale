import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "de.pbz"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.kord", "kord-core", "0.8.0-M8")
    testImplementation(kotlin("test"))

    runtimeOnly("org.slf4j", "slf4j-simple", "1.7.35")
    runtimeOnly("org.slf4j", "slf4j-api", "1.7.35")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

application {
    mainClass.set("MainKt")
}