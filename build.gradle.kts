import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
//    application
}

group = "com.dannybierek.tools.hmc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-Beta")
    implementation("io.github.microutils:kotlin-logging:2.0.10")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.slf4j:slf4j-log4j12:1.7.29")
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    testImplementation(kotlin("test"))

    implementation(compose.desktop.currentOs)
    implementation("androidx.ui:ui-layout:0.1.0-dev03")

    implementation("androidx.compose:compose-compiler:0.1.0-dev09")
    implementation("androidx.compose:compose-runtime:0.1.0-dev09")
    implementation("androidx.ui:ui-layout:0.1.0-dev09")
    implementation("androidx.ui:ui-material:0.1.0-dev09")
    implementation("androidx.ui:ui-tooling:0.1.0-dev09")
    implementation("androidx.ui:ui-framework:0.1.0-dev09")

//    implementation("androidx.compose.material:material-icons-extended:1.4.0")
}


tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
compose.desktop {
    application {
        mainClass = "com.dannybierek.tools.hmc.HitmanModCreatorKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinJvmComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}

//application {
//    mainClass.set("GenerateMissionApplicationKt")
//}