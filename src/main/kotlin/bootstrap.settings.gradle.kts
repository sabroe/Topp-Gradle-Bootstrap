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

import com.yelstream.topp.gradle.boot.BootstrapBuilds
import me.champeau.gradle.igp.gitRepositories

plugins {
    id("me.champeau.includegit")
}

val bootstrapBuild = BootstrapBuilds.of(settings)


gitRepositories {
    // The Gradle property 'bootstrap.git.use.cli' (default: true) chooses the git client.
    // With 'true', the git command line is used, and git authenticates with your own git and SSH setup.
    // With 'false', the plugin's built-in Java git client is used, and it authenticates with the SSH key below.
    // The plugin ignores the authentication with the git command line, and warns "Custom authentication via
    // the authentication DSL is incompatible with use of the git CLI client", so it is set only for 'false'.
    if (!bootstrapBuild.gitUseCLI) {
        defaultAuthentication {
            sshWithPublicKey()
        }
    }
    useGitCli = bootstrapBuild.gitUseCLI
    checkoutsDirectory.set(bootstrapBuild.checkoutsDirectory.toFile())
    include(bootstrapBuild.repositoryName) {
        uri.set(bootstrapBuild.repositoryURL.toString())
        branch.set(bootstrapBuild.repositoryBranch)

        // includeBuild(".")
    includeBuild("module/Topp-Gradle-Feature")
    }
}
