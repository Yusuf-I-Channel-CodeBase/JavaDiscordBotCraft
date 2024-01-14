plugins {
    id("com.diffplug.spotless") version "6.23.3"
    application
}

group = "io.github.realyusufismail" // used for publishing. DON'T CHANGE

repositories { mavenCentral() }

dependencies {
    // json
    implementation("com.fasterxml.jackson.core:jackson-core:" + properties["jacksonCoreVersion"])
    implementation("com.fasterxml.jackson.core:jackson-databind:" + properties["jacksonDatabindVersion"])

    //https and io.github.realyusufismail.javadiscordbotcrafter.ws
    implementation("com.squareup.okhttp3:okhttp:" + properties["okhttp3Version"])
    implementation("com.neovisionaries:nv-websocket-client:" + properties["nvWebsocketClientVersion"])

    //logger
    implementation("ch.qos.logback:logback-classic:" + properties["logBackClassicVersion"])
    implementation("ch.qos.logback:logback-core:" + properties["logBackCoreVersion"])

    // config.json
    implementation("io.github.realyusufismail:jconfig:" + properties["jconfigVersion"])
}


configurations { all { exclude(group = "org.slf4j", module = "slf4j-log4j12") } }

spotless {
    java {
        target("**/*.java")
        googleJavaFormat()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

java {
    toolchain {
        withJavadocJar()
        withSourcesJar()

        languageVersion.set(JavaLanguageVersion.of(17))
    }
}