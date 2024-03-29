plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "dev.amal.tracker_data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":tracker:tracker_domain"))

    implementation(libs.core.ktx)

    // Coroutines
    implementation(libs.coroutines)

    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit)

    // Serialization
    implementation(libs.serialization.json)

    // Serialization Converter
    implementation(libs.serialization.converter)

    // OkHttp
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Desugaring
    coreLibraryDesugaring(libs.desugar.jdk)
}