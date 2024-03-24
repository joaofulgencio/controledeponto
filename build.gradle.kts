plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.equipet0209.com"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("io.ktor:ktor-features:1.6.7") // Include the features module for CallLogging
    implementation("ch.qos.logback:logback-classic:1.2.6") // Logging backend (optional)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("io.ktor:ktor-gson:1.6.7") // Optional for Gson JSON serialization support

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}