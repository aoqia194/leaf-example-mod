plugins {
    id "maven-publish"

    alias(libs.plugins.leaf.loom)
}

base {
    archivesName = project.name
}

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
    mavenLocal()
}

loom {
    clientOnlyZomboidJar()

	mods {
		"modid" {
			sourceSet sourceSets.main
		}
	}
}

dependencies {
    // To change the versions, see gradle/libs.versions.toml
    zomboid(libs.zomboid)
    mappings(variantOf(libs.leaf.yarn) { classifier("v2") })
    modImplementation(libs.leaf.loader)
}

processResources {
    inputs.property "version", project.version
    inputs.property "loader_version", libs.leaf.loader.get().version
    inputs.property "zomboid_version", libs.zomboid.get().version

    filesMatching("leaf.mod.json") {
        expand "version": inputs.properties.version
        expand "loader_version": inputs.properties.loader_verison
        expand "zomboid_version": inputs.properties.zomboid_version
    }
}

tasks.withType(JavaCompile).configureEach {
    options.release = 17
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

jar {
    from("LICENSE") {
        rename {
            "${it}_${inputs.properties.archivesName}"
        }
    }
}

// configure the maven publication
publishing {
    publications {
        create("mavenJava", MavenPublication) {
            artifactId = project.name
            from components.java
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
