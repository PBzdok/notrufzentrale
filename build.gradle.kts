import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
    jacoco
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
    implementation("dev.kord:kord-core:0.8.0-M16")
    implementation("io.arrow-kt:arrow-core:1.1.4-alpha.10")

    testImplementation(kotlin("test"))

    runtimeOnly("org.slf4j:slf4j-simple:2.0.3")
    runtimeOnly("org.slf4j:slf4j-api:2.0.3")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
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

application {
    mainClass.set("NotrufzentraleKt")
}