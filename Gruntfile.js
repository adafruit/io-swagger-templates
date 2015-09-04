'use strict';

module.exports = function(grunt) {

  // Load all grunt tasks
  require('load-grunt-tasks')(grunt);

  // Project configuration.
  grunt.initConfig({
    shell: {
      add_languages: {
        options: {
          stdout: true
        },
        command: [
          'jar uvf swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar -C ./overrides classes/com/adafruit/swagger/codegen/ServerCodegen.class'
        ].join('&&')
      },
      node_server: {
        options: {
          stdout: true
        },
        command: [
          'java -jar swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate -i http://io.adafruit.com/api/docs/api.json -o ~/adafruit-io-node/server -l com.adafruit.swagger.codegen.ServerCodegen',
          'rm ~/adafruit-io-node/server/package.json'
        ].join('&&')
      }
    }
  });

  // Default task.
  grunt.registerTask('default', ['shell:node_server']);

};
