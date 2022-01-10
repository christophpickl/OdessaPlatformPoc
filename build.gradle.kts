
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
    implementation("io.ktor:ktor-server-netty:1.6.4")
    implementation("freemarker:freemarker:2.3.9")
}
