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
    implementation(kotlin("stdlib")) // stdlib 官方示例引入，但是不引入好像也没问题
    implementation(kotlin("reflect")) // 和下面应该是等价的不同写法
    // implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mustache") // HTML 模板库
    // spring boot 用 undertow 换掉 tomcat
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module="spring-boot-starter-tomcat")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // 序列化库

    // @ConfigurationProperties 注解处理器
    kapt("org.springframework.boot:spring-boot-configuration-processor")

//    implementation("com.google.guava:guava:31.1-jre")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:4.0.0")
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