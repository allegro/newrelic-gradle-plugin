package pl.allegro.tech.build.newrelic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.BasePlugin

class NewrelicPlugin implements Plugin<Project> {
    static final def EXTEND_DIST_ZIP_TASK = "extendDistZip";
    static final def ADD_AGENT_TASK = "addAgent";
    static final def NEWRELIC_API_NAME = 'newrelic-api'
    static final def NEWRELIC_AGENT_NAME = 'newrelic-agent'
    static final def NEWRELIC_GROUP = 'com.newrelic.agent.java'

    @Override
    void apply(Project project) {

        def appPlugin = project.plugins.findPlugin(ApplicationPlugin)
        if (!appPlugin) {
            throw new IllegalStateException("Application plugin must be applied before newrelic plugin")
        }
        project.extensions.create("newrelic", NewrelicPluginExtension)

        project.afterEvaluate {
            project.dependencies {
                compile group: NEWRELIC_GROUP,
                        name: NEWRELIC_AGENT_NAME,
                        version: project.extensions.newrelic.version

                compile group: NEWRELIC_GROUP,
                        name: NEWRELIC_API_NAME,
                        version: project.extensions.newrelic.version
            }
        }

        def extendDistZip = project.task(EXTEND_DIST_ZIP_TASK, type: ExtendDistZipTask)
        project.configure(extendDistZip) {
            group = BasePlugin.BUILD_GROUP
            description = 'Add newrelic.yml to distribution package'
        }
        def addAgentTask = project.task(ADD_AGENT_TASK, type: AddAgentTask)
        project.configure(addAgentTask) {
            group = BasePlugin.BUILD_GROUP
            description = 'Add Newrelic agent to startup command'
        }

        project.tasks.distZip.dependsOn addAgentTask
        project.applicationDistribution.from(extendDistZip) {
            into "lib"
        }
    }

}
