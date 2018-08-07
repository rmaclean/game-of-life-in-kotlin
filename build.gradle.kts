plugins {
    application
    kotlin("jvm") version "1.2.60"
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