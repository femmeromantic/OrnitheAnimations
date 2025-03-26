pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.ornithemc.net/releases")
        maven("https://maven.ornithemc.net/snapshots")
    }
}

val mod_name: String by settings

rootProject.name = mod_name
