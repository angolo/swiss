plugins {
    kotlin("jvm")
    id("io.ktor.plugin") version "2.3.12"
    id ("com.google.devtools.ksp") version "2.0.20-1.0.25"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


tasks.jar {
    archiveFileName.set("app.jar")
    manifest {
        attributes["Main-Class"] = "base.main.ApplicationKt"
    }
    destinationDirectory = layout.buildDirectory.dir("$rootDir")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(
        configurations
            .runtimeClasspath
            .get()
            .map(::zipTree)
    )
}

dependencies {
    //ktor + netty
    implementation("io.ktor:ktor-server-core:2.3.12")
    implementation("io.ktor:ktor-server-netty:2.3.12")

    implementation("io.ktor:ktor-server-content-negotiation:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
    implementation("org.mongodb:bson-kotlinx:5.2.0")


    //mongo
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.0")
    //serialization from bson
    implementation("org.mongodb:bson-kotlinx:5.2.0")

    //koin
    implementation("io.insert-koin:koin-ktor:4.0.0")

    val koinKspVersion = "1.3.1"
    implementation("io.insert-koin:koin-annotations:$koinKspVersion")
    ksp("io.insert-koin:koin-ksp-compiler:$koinKspVersion")

    //tests
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
}


tasks.test {
    useJUnitPlatform()
}
