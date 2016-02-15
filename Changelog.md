#Changelog


## 0.2.0
New configuration options

agentDir = "../lib" # default 
loadAgent = true # default

If you're running the application from a different directory the project/bin

```
newrelic {
    ...
    agentDir '/fullpath/to/project_dir/lib
}
```

To disable changing the DEFAULT_JVM_OPTS use

```
newrelic {
    ...
    loadAgent false
}
```
newrelic.yml will still be placed in the project/lib/

And you'll have to manually provide the -javaagent:/fullpath/newrelic-agent.xx.xx.jar at runtime.
