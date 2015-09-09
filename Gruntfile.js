'use strict';

module.exports = function(grunt) {

  // Load all grunt tasks
  require('load-grunt-tasks')(grunt);

  // Project configuration.
  grunt.initConfig({
    'json-replace': {
      inline: {
        files: [
          {
            expand: true,
            flatten: true,
            src: ['../adafruit-io-node/server/swagger.json'],
            dest: '../adafruit-io-node/server/'
          }
        ],
        options: {
          space: '  ',
          replace: {
            "host": "localhost",
            "schemes": [
              "http"
            ]
          }
        }
      }
    },
    shell: {
      node_server: {
        options: {
          stdout: true
        },
        command: [
          'cd node-server',
          'mvn package',
          'cd ..',
          'java -cp swagger-codegen/modules/swagger-codegen-cli/target/lib/*:./node-server/target/* io.swagger.codegen.Codegen -i http://io.adafruit.com/api/docs/api.json -o ~/adafruit-io-node/server -l adafruit.codegen.AdafruitIoServerGenerator',
          'wget --output-document=../adafruit-io-node/server/swagger.json http://io.adafruit.com/api/docs/api.json'
        ].join('&&')
      }
    }
  });

  // Default task.
  grunt.registerTask('default', ['shell:node_server', 'json-replace']);

};
