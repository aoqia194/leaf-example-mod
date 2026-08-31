plugins {
    `maven-publish`

    alias(libs.plugins.leaf.loom)
}

loom {
    mods {
        create(project.name) {
            sourceSet(sourceSets.main.get())
        }
    }
    // Decompiles only the zombie classes instead of every file.
    decompilers {
        getByName("vineflower") {
            options.putAll(mapOf(
                "included-classes" to "zombie.*",
            ))
        }
    }
}

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.

    // Temporary: Loom hasn't included the new maven for Leaf Loader yet.
    // This will be redundant.
    maven {
        name = "Leaf"
        url = uri("https://maven.aoqia.dev/releases/")
    }

    mavenLocal()
}

dependencies {
    // To change the versions, see gradle/libs.versions.toml
    zomboid(libs.zomboid)
    implementation(libs.leaf.loader)
    // implementation(libs.leaf.api)
}

java {
    withSourcesJar()

    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    processResources {
        val projectVersion: String = project.version.toString()
        val loaderVersion: String = libs.versions.leaf.loader.get()
        val zomboidVersion: String = libs.versions.zomboid.get()

        val props = mapOf(
            "version" to projectVersion,
            "loader_version" to loaderVersion,
            "zomboid_version" to zomboidVersion
        )

        inputs.properties(props)

        filesMatching("leaf.mod.json") {
            expand(props)
        }
    }

    jar {
        from("LICENSE") {
            rename {
                "${it}_${project.name}"
            }
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 25
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}
