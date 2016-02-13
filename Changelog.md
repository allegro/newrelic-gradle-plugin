#Changelog


## 0.1.2
New configuration options

libDir = "../lib" # default 
loadNewRelicAgent = true # default

If you're running the application from a different directory the project/bin

```
newrelic {
    ...
    libDir '/fullpath/to/project_dir/lib
}
```

To disable changing the DEFAULT_JVM_OPTS use

```
newrelic {
    ...
    loadNewRelicAgent false
}
```
newrelic.yml will still be placed in the project/lib/

And you'll have to manually provide the -javaagent:/fullpath/newrelic-agent.xx.xx.jar at runtime.
