plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.11"
//    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "org.equipet0209.com"
version = ""

repositories {
    google()
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-core:2.3.11")
    implementation("io.ktor:ktor-server-netty:2.3.11")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.11")
    implementation("io.ktor:ktor-server-cors:2.3.11")
    implementation("io.ktor:ktor-features:1.6.8") // Include the features module for CallLogging
    implementation("ch.qos.logback:logback-classic:1.5.3") // Logging backend (optional)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")
    implementation("io.ktor:ktor-serialization-gson:2.3.11")// Optional for Gson JSON serialization support
    implementation("com.google.firebase:firebase-admin:9.2.0")
    implementation("com.google.guava:guava:33.1.0-jre") // FireBase-admin 9.2.0 uses guava 31.1-jre which have vulnerabilities
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1") // Verifique a versão mais recente
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("io.grpc:grpc-netty-shaded:1.51.0")
    implementation("io.grpc:grpc-core:1.51.0")
    implementation("io.grpc:grpc-stub:1.51.0")
    implementation("io.grpc:grpc-alts:1.51.0")
    implementation("io.grpc:grpc-auth:1.51.0")
    implementation("io.grpc:grpc-okhttp:1.51.0")
    implementation("io.grpc:grpc-services:1.51.0")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}


tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}

tasks.register<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar2") {
    archiveFileName.set("controledeponto.jar")
    archiveClassifier.set("")
    archiveVersion.set("")

    mergeServiceFiles()
    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    from(sourceSets.main.get().output)
    configurations = listOf(project.configurations.getByName("runtimeClasspath"))
}

tasks.build {
    dependsOn("shadowJar")
}