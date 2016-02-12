package pl.allegro.tech.build.newrelic

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class AddAgentTask extends DefaultTask {

    @TaskAction
    void addNewrelic() {
        if (!project.extensions.newrelic.disableDefaultJvmArgs) {
            project.applicationDefaultJvmArgs += [
                    '-javaagent:' +
                            project.extensions.newrelic.libDir +
                            NewrelicPlugin.NEWRELIC_AGENT_NAME +
                            '-' +
                            project.extensions.newrelic.version + '.jar'
            ]
        }
    }

}
