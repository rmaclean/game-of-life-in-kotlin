plugins {
    application
    kotlin("jvm") version "1.2.60"
    id("org.jmailen.kotlinter") version "1.16.0"
}

application {
    mainClassName = "robert.GolKt"
}

dependencies {
    compile(kotlin("stdlib"))
}

repositories {
    jcenter()
}
