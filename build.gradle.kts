buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}