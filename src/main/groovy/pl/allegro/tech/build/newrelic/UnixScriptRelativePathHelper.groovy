package pl.allegro.tech.build.newrelic

class UnixScriptRelativePathHelper {
    static def fixUnixScript(String text) {
        def needsFixing = text.indexOf('DEFAULT_JVM_OPTS=') < text.indexOf('APP_HOME=');
        if (text.indexOf('javaagent:../lib/newrelic') >= 0
                && needsFixing) {

            def jvmArgs = text.find(/(DEFAULT_JVM_OPTS=.*)/)
            def fixedJvmArgs = jvmArgs.replace("../lib/newrelic", "'\$APP_HOME'/newrelic")
            def appHome = text.find(/(APP_HOME=.*)/)

            return text
                    .replace(jvmArgs, "#"+jvmArgs + " # moved after APP_HOME= by " + UnixScriptRelativePathHelper.class.getName().toString())
                    .replace(appHome, appHome + "\n" + fixedJvmArgs
            )
        }
        return text
    }
}
