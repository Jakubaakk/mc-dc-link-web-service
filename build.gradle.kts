import org.flywaydb.gradle.task.FlywayBaselineTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    id("com.google.cloud.tools.jib") version "3.2.1"
    id("org.flywaydb.flyway") version "8.5.13"
}

group = "cz.jakubfilko"
version = "0.1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

val aspectjVersion = "1.9.9.1"

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-mysql:8.5.13")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    runtimeOnly("org.aspectj:aspectjweaver:$aspectjVersion")

    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jib.to.image = "mc-dc-link-ws:local"

flyway {
    url = "jdbc:mysql://localhost:33306/mcdclink?useSSL=false"
    user = "mcdclink"
    password = "mcdclink123"
    schemas = arrayOf("mcdclink")
    locations = arrayOf("classpath:db/migration")
}

tasks.withType<FlywayBaselineTask> {
    dependsOn("classes")
}

tasks.register("currentVersion") {
    doLast {
        println(version)
    }
}
