plugins {
//    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.25"
    id("com.google.devtools.ksp") version "1.9.25-1.0.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.4"
    id("io.micronaut.test-resources") version "4.4.4"
    id("io.micronaut.aot") version "4.4.4"
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    kotlin("jvm") version "1.9.0"
}

version = "0.1"
group = "com.mysql"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    ksp("io.micronaut.data:micronaut-data-processor")
    ksp("io.micronaut:micronaut-http-validation")
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.data:micronaut-data-jdbc")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("io.micronaut:micronaut-http-client")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    implementation("io.micronaut:micronaut-inject-java")
}

application {
    mainClass = "com.mysql.ApplicationKt"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
}

graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.mysql.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}

// Add ktlint configuration
ktlint {
    android.set(false)
    outputColorName.set("RED")
    outputToConsole.set(true)
    disabledRules.set(setOf("no-wildcard-imports"))
}

tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask>().configureEach {
    dependsOn("compileKotlin")
}


tasks.named<Jar>("jar") {
    archiveBaseName.set("mysqlRestApi")
    archiveVersion.set("1.0.0")
    archiveClassifier.set("")
}


