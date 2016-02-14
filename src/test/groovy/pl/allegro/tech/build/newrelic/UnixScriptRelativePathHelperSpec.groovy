package pl.allegro.tech.build.newrelic

import spock.lang.Specification

class UnixScriptRelativePathHelperSpec extends Specification {
    def "should change position of DEFAULT_JVM_OPTS" () {
        given:
        def input = """
line 1
DEFAULT_JVM_OPTS='"a" "-javaagent:../lib/newrelic-a.b-c.jar" "c"'
line 3
APP_HOME="`pwd -P`"
line 5
"""
        when:
        def result = UnixScriptRelativePathHelper.fixUnixScript(input)

        then:

        assert result == """
line 1
#DEFAULT_JVM_OPTS='"a" "-javaagent:../lib/newrelic-a.b-c.jar" "c"' # moved after APP_HOME= by pl.allegro.tech.build.newrelic.UnixScriptRelativePathHelper
line 3
APP_HOME="`pwd -P`"
DEFAULT_JVM_OPTS='"a" "-javaagent:'\$APP_HOME'/newrelic-a.b-c.jar" "c"'
line 5
"""
    }

    def "should not change unixScript" () {
        when:
        def result = UnixScriptRelativePathHelper.fixUnixScript(input)

        then:
        assert result == input

        where:
        input << [
"""
APP_HOME=/home
DEFAULT_JVM_OPTS=-javaagent:../lib/newrelic-a.b.c.jar
""",
"""
DEFAULT_JVM_OPTS=newrelic-agent-missing
APP_HOME=/home
"""

                ]
    }
}
