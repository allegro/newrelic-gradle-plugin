package pl.allegro.tech.build.newrelic

import groovy.text.SimpleTemplateEngine
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class ExtendDistZipTask extends DefaultTask {

    private final templateEngine = new SimpleTemplateEngine()

    private final static FILE_NAME = "newrelic.yml"

    @OutputDirectory
    def destinationDir = project.file("${project.buildDir}/newrelic")

    @TaskAction
    void extend() {
        def outputFile = new File(destinationDir, FILE_NAME)
        def templateFileContent = getTemplateFromProject() ?: getTemplateFromPlugin()

        def binding = [
                'licenseKey'  : project.extensions.newrelic.licenseKey,
                'projectName' : project.extensions.newrelic.name ?: project.name
        ]

        def newrelicFileContent = templateEngine.createTemplate(templateFileContent).make(binding)

        outputFile.write(newrelicFileContent as String)
    }

    private String getTemplateFromPlugin() {
        return this.class.getResourceAsStream(FILE_NAME).text
    }

    private String getTemplateFromProject() {
        def result = this.project.sourceSets.main.resources.filter { File it ->
            it.name == FILE_NAME
        }

        if (result.isEmpty()) {
            return null
        }

        return result.getSingleFile().text
    }
}
