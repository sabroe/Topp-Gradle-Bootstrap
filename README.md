# Topp Gradle Bootstrap

The purpose of this is to bootstrap access to a Gradle script library of choice,
per default the one contained in the GitHub repository `Topp-Gradle-Convention`.

How to bootstrap a Gradle multi-module project of your own:

1. Ensure that this bootstrap repository has been cloned using Git. \
   On Linux, `git clone https://github.com/sabroe/Topp-Gradle-Bootstrap.git ~/.topp/gradle/script/Topp-Gradle-Bootstrap` \
   On Windows, `git clone https://github.com/sabroe/Topp-Gradle-Bootstrap.git %USERPROFILE%\.topp\gradle\script\Topp-Gradle-Bootstrap`
2. In the main settings file of your own multi-module project, 
   e.g. a Kotlin DSL `settings.gradle.kts`,
   refer first to the location of the cloned bootstrap repository using `includeBuild(...)`
   and second to the specific script `bootstrap.setings.gradle.kts` as the plugin with id `bootstrap`.

In your `settings.gradle.kts`, this may appear like this:

```
rootProject.name = "My-Super-Project"

pluginManagement {
    includeBuild("${System.getProperty("user.home")}/.topp/gradle/script/Topp-Gradle-Bootstrap")
}

plugins {
    id("bootstrap")
}

include("my-module-1")
include("my-module-2")
include("my-module-3")
```

For more specific uses,
see [Topp-Gradle-Convention](https://github.com/sabroe/Topp-Gradle-Convention)
