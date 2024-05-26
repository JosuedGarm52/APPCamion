plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.camionapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.camionapi"
        minSdk = 25
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    // Habilitar View Binding
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}


dependencies {
    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxAppcompat)
    implementation(libs.material)
    implementation(libs.androidxActivity)
    implementation(libs.androidxConstraintlayout)
    implementation(libs.androidxNavigationFragmentKtx)
    implementation(libs.androidxNavigationUiKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.androidxEspressoCore)


    // Room dependencies
    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.glide)
}

