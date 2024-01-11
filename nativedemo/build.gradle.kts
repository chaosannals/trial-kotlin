plugins {
    application
    kotlin("multiplatform") version "1.9.0"
}

group = "me.uuuu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
        hostOs == "Linux" && isArm64 -> linuxArm64("native")
        hostOs == "Linux" && !isArm64 -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
//        compilations.getByName("main") {
//            cinterops {
//                val libcurl by creating {
//                    defFile(project.file("src/nativeInterop/cinterop/libcurl.def"))
//                    packageName("com.jetbrains.handson.http")
//                    compilerOpts("-I/path")
//                    includeDirs.allHeaders("path")
//                }
//            }
//        }
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    sourceSets {
        val ktorVersion = "2.3.7" // ktor native 不支持 Windows
        val nativeMain by getting {
            dependencies {
//                implementation("io.ktor:ktor-server-core:$ktorVersion")
//                implementation("io.ktor:ktor-server-cio:$ktorVersion")
            }
        }
        val nativeTest by getting {
            dependencies {
                implementation(kotlin("test"))
//                implementation("io.ktor:ktor-server-test-host:$ktorVersion")
            }
        }
    }
}

dependencies {
//    commonMainImplementation("org.ktorm:ktorm-core:3.6.0")
}