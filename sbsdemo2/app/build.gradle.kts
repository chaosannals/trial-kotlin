import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // kotlin 1.7.10 支持 JVM20
    // kotlin 1.8.0 不支持 JVM20
    // kotlin 1.9.22 支持 JVM20

    val ktVersion = "1.9.22"

    kotlin("jvm") version ktVersion
    kotlin("plugin.spring") version ktVersion
    kotlin("plugin.jpa") version ktVersion
    kotlin("plugin.allopen") version ktVersion
    kotlin("kapt") version ktVersion // 注解处理器

    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mustache") // HTML 模板库
    // spring boot 用 undertow 换掉 tomcat
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module="spring-boot-starter-tomcat")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // 序列化库
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // @ConfigurationProperties 注解处理器
    kapt("org.springframework.boot:spring-boot-configuration-processor")

//    implementation("com.google.guava:guava:31.1-jre")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

application {
    mainClass.set("sbsdemo2.AppKt")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
  }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}