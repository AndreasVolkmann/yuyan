plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"
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
    val ktorVersion = "3.1.2"
    val arkenvVersion = "3.3.3"

    implementation("com.azure:azure-ai-openai:1.0.0-beta.16")
    implementation("com.microsoft.cognitiveservices.speech", "client-sdk", "1.43.0", null, null, "jar")

    implementation("com.apurebase:arkenv:${arkenvVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${coroutinesVersion}")
    implementation("io.ktor:ktor-client-core:${ktorVersion}")
    implementation("io.ktor:ktor-client-cio:${ktorVersion}")
    implementation("io.ktor:ktor-client-content-negotiation:${ktorVersion}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
    
    testImplementation(kotlin("test"))
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
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}

tasks.test {
    useJUnitPlatform()
}