plugins {
    id("com.android.application") version "8.3.2"
    id("org.jetbrains.kotlin.android") version "1.6.21"
    id("androidx.navigation.safeargs.kotlin")
    //id("com.google.devtools.ksp") version "1.7.0"
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
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.activity:activity-ktx:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // Room dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.github.bumptech.glide:glide:4.14.2")

    val lifeCycle = "2.7.0"

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycle")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifeCycle")

    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:2.6.1")
}

