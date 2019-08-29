# maven-tomcat-embedded-archetype
The archetype for generating embedded tomcat web project.


The generated layout will be: 

```
root
|-- pom.xml
`-- src
    |-- main
        |`-- java
        |    `-- ${yourPackageName}
        |        `-- launch
        |            `-- TomcatEmbeddedBootstrap.java
        `-- webapp
            `-- index.jsp
            `-- WEB-INF
                `-- web.xml
```

## Build

1. generate tomcat project by archetyping
```cmd
mvn -B  archetype:generate "-DgroupId=${yourGroupId}" "-DartifactId=${yourArtifact}" "-Dpackage=${yourPackageName}" "-DarchetypeGroupId=com.teampathy" "-DarchetypeArtifactId=tomcat-embedded-archetype" "-Dversion=1.2-SNAPSHOT"
```

2. build and start the tomcat server
```cmd
cd ${yourGroupId}
mvn package
./target/bin/startup.bat
```

3. Type 127.0.0.1:8080 (defaulted at 8080 port) in the browser's url location, then you can see the hello world jsp.
