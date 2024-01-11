plugins {
    id("com.google.devtools.ksp") version "1.9.21-1.0.16"
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("mysql:mysql-connector-java:8.0.33")

    val ktormVersion = "3.6.0"
//    val ktormVersion = "3.7.0-SNAPSHOT"
    implementation("org.ktorm:ktorm-core:$ktormVersion")
//    implementation("org.ktorm:ktorm-jackson:$ktormVersion") // 这个 mavenCentral 版本好低
    implementation("org.ktorm:ktorm-support-mysql:$ktormVersion")
//    implementation("org.ktorm:ktorm-ksp-annotations:$ktormVersion") // 这个 mavenCentral 版本好低

//    ksp("org.ktorm:ktorm-ksp-compiler:$ktormVersion")
    ksp("org.ktorm:ktorm-ksp-compiler:1.0.0-RC3") // 不知道为啥，没有把同版本的发布到 mavenCentral

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}