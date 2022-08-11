plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    // 这个插件会影响到版本。不指定版本会出现不同引入的默认版本冲突。
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
}
