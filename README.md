[![Build Status](https://travis-ci.org/allegro/newrelic-gradle-plugin.svg?branch=master)](https://travis-ci.org/allegro/newrelic-gradle-plugin) 

newrelic-gradle-plugin
====

This plugin facilitates the integration of your app with [NewRelic] (http://newrelic.com/).

## Basic usage

###Configuration:

```
buildscript {
    repositories {
         maven {
            url "https://plugins.gradle.org/m2/"
         }
    }
    dependencies {
        classpath group: 'pl.allegro.tech.build', name: 'newrelic-gradle-plugin', version: '0.1.2'
    }
}
apply plugin: 'application'
apply plugin: 'pl.allegro.tech.build.newrelic-gradle-plugin'

newrelic {
    licenseKey '#1231afa2441251asda' //required - newrelic account key
    version '3.25.0' //optional - default '3.25.0'
    name 'AwesomeApp' // optional - module name by default
}
```

### Use:
```
./gradlew clean distZip
```

## Advanced Usage
### Agent configuration
You can also create an own [newrelic.yml] (https://docs.newrelic.com/docs/agents/java-agent/configuration/java-agent-config-file-template) to configure it as suit you the best.
Finally this file should be placed on root classpath of your built application.

### Agent directory
To initialize the agent properly is important to run application from "bin" directory. In some cases this is not possible. Then the path to agent directory needs to be set by *agentDir* option. By default property have “../lib” value.  You can also disable loading agent entirely by *loadAgent* option.   
```groovy
newrelic {
    //...
    agentDir: "/path/to/agent/directory"
    loadAgent: true
    //...
}
```


## Requirements
* newrelic-gradle-plugin must be applied after application plugin.
* environment must be pointed by setting -Dnewrelic.environment= on the Java startup command. By default the plugin contains configuration for two environments: "prod" and "test".

## License

**newrelic-gradle-plugin** is published under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

