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

package com.yelstream.topp.gradle.boot

import org.gradle.api.initialization.Settings
import org.gradle.api.provider.ProviderFactory
import java.net.URI
import java.nio.file.Path

data class BootstrapBuilds(
    val gitUseCLI: Boolean,
    val checkoutsDirectory: Path,
    val repositoryName: String,
    val repositoryURL: URI,
    val repositoryBranch: String) {

    companion object {
        fun of(settings: Settings): BootstrapBuilds {
            val providers: ProviderFactory = settings.providers

            val gitUseCLI: Boolean =
                providers.gradleProperty("bootstrap.git.use.cli").map { it.toBoolean() }
                    .getOrElse(true)

            val checkoutsDirectoryStr: String =
                providers.gradleProperty("bootstrap.git.checkouts.directory")
                    .getOrElse("${System.getProperty("user.home")}/.topp/gradle/script/")

            val checkoutsDirectory =
                Path.of(checkoutsDirectoryStr)

            val repositoryName: String =
                providers.gradleProperty("bootstrap.git.repo.name")
                    .getOrElse("Topp-Gradle-Convention")

            val repositoryURL: URI =
                providers.gradleProperty("bootstrap.git.repo.uri").map { URI.create(it) }
                    .getOrElse(URI("https://github.com/sabroe/Topp-Gradle-Convention.git"))

            val repositoryBranch: String =
                providers.gradleProperty("bootstrap.git.repo.branch")
                    .getOrElse("main")

            return BootstrapBuilds(
                gitUseCLI = gitUseCLI,
                checkoutsDirectory = checkoutsDirectory,
                repositoryName = repositoryName,
                repositoryURL = repositoryURL,
                repositoryBranch = repositoryBranch
            )
        }
    }
}
