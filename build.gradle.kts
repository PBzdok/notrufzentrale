import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "de.pbz"
version = "1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.kord:kord-core:0.8.0-M15")
    implementation("io.arrow-kt:arrow-core:1.1.2")

    testImplementation(kotlin("test"))

    runtimeOnly("org.slf4j:slf4j-simple:1.7.36")
    runtimeOnly("org.slf4j:slf4j-api:1.7.36")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "NotrufzentraleKt"
    }
    configurations["compileClasspath"]
        .forEach { file: File ->
            from(zipTree(file.absoluteFile))
        }
}

application {
    mainClass.set("NotrufzentraleKt")
}