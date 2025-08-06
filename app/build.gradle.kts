plugins {
    application
    id("java")
    id("com.github.ben-manes.versions") version "0.39.0"
}

application {
    mainClass = "hexlet.code.App"
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

    //<groupId>com.fasterxml.jackson.core</groupId>
    //<artifactId>jackson-databind</artifactId>
    //<version>2.17.2</version>
}

tasks.test {
    useJUnitPlatform()
}