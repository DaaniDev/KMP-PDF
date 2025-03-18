plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("maven-publish")
}
group = "com.github.DaaniDev"
version = "1.0.0"
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.material)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.kotlin.coroutines)
            implementation(libs.kotlinx.datetime)
        }
        iosMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    publishing {
        publications {
            // This ensures all targets are published
            withType<MavenPublication> {
                // Optional: Add POM information
                pom {
                    name.set("KMP-PDF")
                    description.set("This library help you to convert your composables into pdf file.")
                    url.set("https://github.com/DaaniDev/KMP-PDF")
                    licenses {
                        license {
                            name.set("The License Name")
                            url.set("License URL")
                        }
                    }
                    developers {
                        developer {
                            id.set("DaaniDev")
                            name.set("Muhammad Danish")
                            email.set("mdanish012@gmail.com")
                        }
                    }
                }
            }
        }
    }
}

android {
    namespace = "com.daanidev.kmp_pdf"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
