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

    // https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc
    implementation("com.microsoft.sqlserver:mssql-jdbc:12.8.1.jre11")
}

tasks.test {
    useJUnitPlatform()
}