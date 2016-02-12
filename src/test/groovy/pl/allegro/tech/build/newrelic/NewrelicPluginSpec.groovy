package pl.allegro.tech.build.newrelic

import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class NewrelicPluginSpec extends Specification {

    Project project

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "newrelic tasks should be added"() {
        when:
        project.plugins.apply(ApplicationPlugin)
        project.plugins.apply(NewrelicPlugin)

        then:
        project.tasks.collect({ task -> task.name }).containsAll(
                NewrelicPlugin.ADD_AGENT_TASK,
                NewrelicPlugin.EXTEND_DIST_ZIP_TASK
        )
    }

    def "newrelic extensions are available"() {
        when:
        project.plugins.apply(ApplicationPlugin)
        project.plugins.apply(NewrelicPlugin)

        then:
        project.extensions.newrelic
        project.extensions.newrelic.hasProperty('licenseKey')
        project.extensions.newrelic.hasProperty('version')
        project.extensions.newrelic.hasProperty('name')
        project.extensions.newrelic.hasProperty('libDir')
        project.extensions.newrelic.hasProperty('disableDefaultJvmArgs')
    }

    def "IllegalStateException should be thrown when application plugin is not applied before NewrelicPlugin"() {
        when:
        project.plugins.apply(NewrelicPlugin)
        project.plugins.apply(ApplicationPlugin)

        then:
        thrown(IllegalStateException)
    }
}
