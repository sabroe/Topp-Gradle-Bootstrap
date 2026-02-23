/*
 * Project: Topp Gradle Bootstrap
 * GitHub: https://github.com/sabroe/Topp-Gradle-Bootstrap
 *
 * Copyright 2022-2026 Morten Sabroe Mortensen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import me.champeau.gradle.igp.gitRepositories

plugins {
    id("me.champeau.includegit")
}

val gitUseCLI = settings.providers.gradleProperty("bootstrap.git.use.cli").map{it.toBoolean()}.getOrElse(true)
val checkoutsDirectory = settings.providers.gradleProperty("bootstrap.git.checkouts.directory").getOrElse("${System.getProperty("user.home")}/.topp/gradle/script/")
val checkoutsDirectoryFile = java.io.File(checkoutsDirectory)
var repositoryName = settings.providers.gradleProperty("bootstrap.git.repo.name").getOrElse("Topp-Gradle-Convention")
var repositoryURL = settings.providers.gradleProperty("bootstrap.git.repo.uri").getOrElse("https://github.com/sabroe/Topp-Gradle-Convention.git")
var repositoryBranch = settings.providers.gradleProperty("bootstrap.git.repo.branch").getOrElse("main")

gitRepositories {
    useGitCli = gitUseCLI
    checkoutsDirectory.set(checkoutsDirectoryFile)
    include(repositoryName) {
        uri.set(repositoryURL)
        branch.set(repositoryBranch)
        // includeBuild(".")
    }
}
