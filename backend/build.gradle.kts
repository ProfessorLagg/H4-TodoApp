plugins {
    id("java")
}

group = "net.laggserver"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.json/json
    implementation("org.json:json:20240303")
}

tasks.test {
    useJUnitPlatform()
}