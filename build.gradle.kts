
repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

application {
    mainClass.set("odessa.App")
}

dependencies {
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("io.ktor:ktor-freemarker:1.6.7")
    implementation("freemarker:freemarker:2.3.9")
    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation("io.github.microutils:kotlin-logging:2.0.6")
}
