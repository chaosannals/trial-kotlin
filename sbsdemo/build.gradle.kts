plugins {
    kotlin("jvm") version "1.7.10"
    // kotlin("multiplatform") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    kotlin("plugin.jpa") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"

    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // kotlin
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0-RC")

    // spring boot
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mustache")

    // spring boot 用 undertow 换掉 tomcat
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module="spring-boot-starter-tomcat")
    }

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.mybatis:mybatis:3.5.10")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

application {
    mainClass.set("trial.AppKt")
}

// tasks.withType<KotlinCompile> {
//   kotlinOptions {
//     freeCompilerArgs = listOf("-Xjsr305=strict")
//   }
// }