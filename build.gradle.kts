plugins {
    `maven-publish`

    alias(libs.plugins.leaf.loom)
}

loom {
    clientOnlyZomboidJar()

    mods {
        create(project.name) {
            sourceSet(sourceSets.main.get())
        }
    }
}

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
}

dependencies {
    // To change the versions, see gradle/libs.versions.toml
    zomboid(libs.zomboid)
    mappings(variantOf(libs.leaf.yarn) { classifier("v2") })
    modImplementation(libs.leaf.loader)
}

base {
    archivesName = project.name
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    processResources {
        inputs.properties(mapOf(
            "version" to project.version,
            "loader_version" to libs.versions.leaf.loader.get(),
            "zomboid_version" to libs.versions.zomboid.get()
        ))

        filesMatching("leaf.mod.json") {
            expand(
                "version" to inputs.properties["version"],
                "loader_version" to inputs.properties["loader_version"],
                "zomboid_version" to inputs.properties["zomboid_version"]
            )
        }
    }

    jar {
        from("LICENSE") {
            rename {
                "${it}_${inputs.properties["archivesName"]}"
            }
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 17
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.name
            from(components.getByName("java"))
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
