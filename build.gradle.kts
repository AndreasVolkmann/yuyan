plugins {
    val kotlinVersion = "2.0.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("com.github.ben-manes.versions") version "0.52.0"
    application
}

group = "me.avo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val coroutinesVersion = "1.10.2"
    val ktorVersion = "3.2.0"
    val arkenvVersion = "3.3.3"
    val junitJupterVersion = "5.13.1"

    implementation("com.microsoft.cognitiveservices.speech", "client-sdk", "1.44.0", null, null, "jar")

    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("com.apurebase:arkenv:${arkenvVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${coroutinesVersion}")
    implementation("io.ktor:ktor-client-core:${ktorVersion}")
    implementation("io.ktor:ktor-client-cio:${ktorVersion}")
    implementation("io.ktor:ktor-client-content-negotiation:${ktorVersion}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
    
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitJupterVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", junitJupterVersion)
    testImplementation("io.mockk:mockk:1.14.3")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${coroutinesVersion}")
}

application {
    mainClass.set("me.avo.MainKt")
}

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(
            listOf(
                "compileJava",
                "compileKotlin",
                "processResources"
            )
        ) // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents) {
            exclude("META-INF/*.RSA")
            exclude("META-INF/*.SF")
            exclude("META-INF/*.DSA")
        }
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during the build
    }
}

tasks.test {
    useJUnitPlatform()
}