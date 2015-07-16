package pl.allegro.tech.build.newrelic

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class AddAgentTask extends DefaultTask {

    @TaskAction
    void addNewrelic() {
        project.applicationDefaultJvmArgs += [
                "-javaagent:$( cd "$( echo "${BASH_SOURCE[0]%/*}" )" && pwd )/../lib/${NewrelicPlugin.NEWRELIC_AGENT_NAME}-${project.extensions.newrelic.version}.jar"
        ]
    }

}
