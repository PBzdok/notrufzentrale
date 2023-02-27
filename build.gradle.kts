import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    application
    idea
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

group = "de.pbz"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.kord:kord-core:0.8.0-M17")
    implementation("io.arrow-kt:arrow-core:1.1.4-alpha.10")

    testImplementation(kotlin("test"))

    runtimeOnly("org.slf4j:slf4j-simple:2.0.3")
    runtimeOnly("org.slf4j:slf4j-api:2.0.3")
}

application {
    mainClass.set("NotrufzentraleKt")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "NotrufzentraleKt"
    }
    configurations["compileClasspath"]
        .forEach { file: File ->
            from(zipTree(file.absoluteFile))
        }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.koverHtmlReport)
}

idea {
    module {
        isDownloadJavadoc = true
    }
}