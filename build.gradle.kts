@file:Suppress("PropertyName")

plugins {
    kotlin("jvm") version "2.0.21"
    id("fabric-loom") version "1.10-SNAPSHOT"
    id("ploceus") version "1.10-SNAPSHOT"
}

val mod_name: String by project
val mod_version: String by project
val mod_group: String by project

version = "$mod_version+mc${libs.versions.minecraft}"
group = mod_group

repositories {
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

dependencies {
    minecraft(libs.minecraft)
    mappings(ploceus.featherMappings(libs.versions.feather.get()))
    ploceus.dependOsl(libs.versions.osl.get())

    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.kotlin)

    include(libs.mixinextras)
    implementation(libs.mixinextras)
    annotationProcessor(libs.mixinextras)

    modImplementation(libs.modmenu)

    modRuntimeOnly(libs.devauth)
}

tasks {
    processResources {
        inputs.property("version", version)

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version))
        }
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${base.archivesName.get()}" }
        }
    }
}
