plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.7"
}

group = "dev.xlfrie.megaviir"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("net.minestom:minestom-snapshots:461c56e749")
    implementation("ch.qos.logback:logback-classic:1.5.6")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "dev.xlfrie.megaviir.Main"
    }
}