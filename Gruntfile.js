'use strict';

module.exports = function(grunt) {

  // Load all grunt tasks
  require('load-grunt-tasks')(grunt);

  // Project configuration.
  grunt.initConfig({
    shell: {
      node_server: {
        options: {
          stdout: true
        },
        command: [
          'java -jar ./swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate -i http://io.adafruit.com/api/docs/api.json -o ./build/adafruit-io-node/server -l nodejs -t ./node/server',
          'find ./node -not -name "*.mustache" -exec cp -r "{}" ./build/adafruit-io-node \\;',
          'rm ./build/adafruit-io-node/server/package.json
        ].join('&&')
      }
    },
  });

  // Default task.
  grunt.registerTask('default', ['shell:node_server']);

};
