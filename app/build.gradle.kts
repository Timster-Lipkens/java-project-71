plugins {
    application
    id("java")
    id("com.github.ben-manes.versions") version "0.39.0"

    checkstyle //всё равно не работает от перемены места
    id("org.sonarqube") version "6.2.0.5505"
    jacoco //нужно ещё что-то?
}

application {
    mainClass = "hexlet.code.App"
}

sonar {
    properties {
        property("sonar.projectKey", "Timster-Lipkens_java-project-71")
        property("sonar.organization", "timster-lipkens")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")

    implementation("org.junit.jupiter:junit-jupiter-engine:5.11.0-M2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<Test>("test") { //нужно или избыточно?
    useJUnitPlatform()
}