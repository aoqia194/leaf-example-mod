pluginManagement {
	repositories {
		maven {
			name = "Leaf"
			url = uri("https://maven.aoqia.dev/releases/")
		}
		maven {
			name = "Fabric"
			url = uri("https://maven.fabricmc.net/")
		}
		mavenCentral()
		gradlePluginPortal()
	}
}

val name: String by settings
rootProject.name = name
