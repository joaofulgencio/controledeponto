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
    implementation("io.ktor:ktor-server-core:2.3.9")
    implementation("io.ktor:ktor-server-netty:2.3.9")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.9")
    implementation("io.ktor:ktor-features:1.6.8") // Include the features module for CallLogging
    implementation("ch.qos.logback:logback-classic:1.5.3") // Logging backend (optional)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")
    implementation("io.ktor:ktor-serialization-gson:2.3.9")// Optional for Gson JSON serialization support
    implementation("com.google.firebase:firebase-admin:9.2.0")
    implementation("com.google.guava:guava:33.1.0-jre") // FireBase-admin 9.2.0 uses guava 31.1-jre which have vulnerabilities

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