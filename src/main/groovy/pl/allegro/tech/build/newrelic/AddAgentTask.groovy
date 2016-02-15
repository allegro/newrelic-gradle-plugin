package pl.allegro.tech.build.newrelic

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import static pl.allegro.tech.build.newrelic.NewrelicPlugin.NEWRELIC_AGENT_NAME


class AddAgentTask extends DefaultTask {

    @TaskAction
    void addNewrelic() {
        if (project.extensions.newrelic.loadAgent) {
            project.applicationDefaultJvmArgs += [
                    '-javaagent:' +
                            project.extensions.newrelic.agentDir +
                            NEWRELIC_AGENT_NAME +
                            '-' +
                            project.extensions.newrelic.version + '.jar'
            ]
        }
    }

}
