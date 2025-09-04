import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.buildSteps.signPathSubmitSigningRequest

version = "2024.12"

project {

  buildType {
    name = "Build and Sign"
    id("build_and_sign")

    vcs {
      root(DslContext.settingsRoot)
      cleanCheckout = true
    }

    // correct Org: 9ff791fc-c563-44e3-ab8c-86a33c910bbe
    // correct apiToken apiToken = "credentialsJSON:a03ec855-c92c-4f33-8877-b8ab1726afd4"

   steps {  

      // sign step
      signPathSubmitSigningRequest {
        connectorUrl = "https://teamcity-connector-stable.customersimulation.int.signpath.io"     
        organizationId = "9ff791fc-c563-44e3-ab8c-86a33c910bbe"
        apiToken = "credentialsJSON:a03ec855-c92c-4f33-8877-b8ab1726afd4"
        projectSlug = "Stefans_Teamcity_Project"
        signingPolicySlug = "test-signing"
        artifactConfigurationSlug = "initial"
        inputArtifactPath = "Loop.ps1"
        outputArtifactPath = "Loop-signed.ps1"
        waitForCompletion = true
         parameters = """
                    version: 1.0.0
                    other_param: other value
                """.trimIndent()
      }
    }

    // publish the signed artifact
    artifactRules = "Loop-signed.ps1"

    // max build duration CONFIGURABLE mins
    failureConditions {
      executionTimeoutMin = 0
    }
  }
}
