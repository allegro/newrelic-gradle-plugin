package pl.allegro.tech.build.newrelic

class NewrelicPluginExtension {

    String licenseKey
    String version = "3.25.0"
    String name
    String libDir = "../lib/"
    boolean disableDefaultJvmArgs = false;

    NewrelicPluginExtension() {
    }
}
