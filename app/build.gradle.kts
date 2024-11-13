plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.varma.hemanshu.starwars_blasters"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.varma.hemanshu.starwars_blasters"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding {
        enable = true
    }
    dataBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation graph
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)

    //viewmodel and livedata
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata)

    //glide
    implementation(libs.glide)

    // Moshi for converting JSON to Kotlin objects(POJO)
    implementation(libs.moshi.kotlin)

    // Retrofit with Moshi Converter
    implementation(libs.retrofit.moshi)

    // Network Logger
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logger.interceptor)

    // Gson
    implementation(libs.gson)
}