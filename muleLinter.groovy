mule_linter {
    rules {
        /* CICD */
        AZURE_PIPELINES_EXISTS {}
        
        /* CONFIGURATION */
        API_CONSOLE_DISABLED{}

        CONFIG_FILE_NAMING {
            format = 'KEBAB_CASE'
        }
        COMMENTED_CODE{}
        COMPONENT_ATTRIBUTE_VALUE {
            component = 'flow-ref'
            namespace = 'http://www.mulesoft.org/schema/mule/core'
            requiredAttributes = ['name']
        }
        /* CONFIG_PLACEHOLDER {
            placeholderAttributes = ['key', 'password', 'keyPassword', 'username', 'host']
        }*/
        // CRON_EXPRESSION_EXTERNALIZED{}
        COMPONENT_DISPLAY_NAME {
            components = [
                    [name: 'set-payload', namespace: "http://www.mulesoft.org/schema/mule/core", displayName: 'Set Payload'],
                    [name: 'set-variable', namespace: "http://www.mulesoft.org/schema/mule/core", displayName: 'Set Variable'],
                    [name: 'transform', namespace: "http://www.mulesoft.org/schema/mule/ee/core", displayName: 'Transform Message'],
                    [name: 'flow-ref', namespace: "http://www.mulesoft.org/schema/mule/core", displayName: 'Flow Reference'],
                    [name: 'foreach', namespace: "http://www.mulesoft.org/schema/mule/core", displayName: 'For Each'],
                    [name: 'parallel-foreach', namespace: "http://www.mulesoft.org/schema/mule/core", displayName: 'Parallel For Each'],
                    [name: 'remove-variable', namespace: "http://www.mulesoft.org/schema/mule/core", displayName: 'Remove Variable'],
                   // [name: 'logger', namespace: "http://www.mulesoft.org/schema/mule/core", displayName: 'Logger'],
                    [name: 'custom-logger', namespace: "http://www.mulesoft.org/schema/mule/cb-core", displayName: 'Custom logger']
            ]
        }
        FLOW_SUBFLOW_NAMING {
            format = 'KEBAB_CASE'
        }
        GLOBAL_CONFIG_NO_FLOWS {
            globalFileName = 'global.xml'
        }

        LOGGER_MESSAGE_HASVALUE {}

        /* MULE_CONFIG_SIZE {
            flowLimit = 25
        } */

        // This rule does not help us much because the common error handler does the logging
        //ON_ERROR_LOG_EXCEPTION{}

        UNUSED_FLOW {}

        /* GIT */
        /* Rule to u Studio/ Groovy stuff in .gitignore file */
        GIT_IGNORE {
            ignoredFiles = ['*.jar', '*.class', 'target/', 'build', 'reports/']
        }

        /* MULE ARTIFACT */
        MULE_ARTIFACT_SECURE_PROPERTIES{}
        MULE_ARTIFACT_MIN_MULE_VERSION{}

        /* POM */
        /* these rules do not add a ton of value */
        /* MAVEN_PROPERTY {
            propertyName = 'cloudhubWorkers'
            propertyValue = '2'
        }
        MULE_MAVEN_PLUGIN {
            version = '3.3.5'
        }
        MULE_RUNTIME {
            version = '4.4.0'
        }
        MUNIT_VERSION {
            version = '2.3.6'
        }
        MUNIT_MAVEN_PLUGIN_ATTRIBUTES {
            coverageAttributeMap =[
                'runCoverage':'true',
                'failBuild':'true',
                'requiredApplicationCoverage':'80',
                'requiredResourceCoverage':'80',
                'requiredFlowCoverage':'80'
            ]
            includeDefaults = false
        }
        */
        // Check Maven Dependency for Custom Logger
        POM_DEPENDENCY_VERSION {
            groupId = 'com.avioconsulting.mule'
            artifactId = 'mule-custom-logger'
            artifactVersion = '2.0.1'
            versionOperator = 'EQUAL'
        }


        /* PROPERTY */
        ENCRYPTED_VALUE {}
        /* HOSTNAME_PROPERTY {
            exemptions = []
        } */
        PROPERTY_FILE_NAMING {
            environments = ['local', 'dev','qa','prod']
            pattern = 'api.${env}.properties'
        }
        /* PROPERTY_EXISTS {
            environments = ['local', 'dev','qa','prod']
            propertyName = 'db.user'
            pattern = 'api.${env}.properties'
        }
        PROPERTY_FILE_COUNT_MISMATCH {
            environments = ['local', 'dev','qa','prod']
            pattern = 'api.${env}.properties'
        } */

        /* README */
        README {}

    }
}