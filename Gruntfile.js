'use strict';

module.exports = function(grunt) {

  // Load all grunt tasks
  require('load-grunt-tasks')(grunt);

  // Project configuration.
  grunt.initConfig({
    'replace': {
      inline: {
        files: [
          {
            expand: true,
            flatten: true,
            src: ['..//adafruit-io-node/server/swagger.yaml'],
            dest: '../adafruit-io-node/server/'
          }
        ],
        options: {
          usePrefix: false,
          patterns: [
            {
              match: 'host: "io.adafruit.com"',
              replacement: 'host: "localhost"'
            },
            {
              match: /-\s\"https\"\n/g,
              replacement: ''
            }
          ]
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
          'java -jar swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate -i http://io.adafruit.com/api/docs/api.json -o ~/adafruit-io-node/server -l swagger-yaml',
          'rm ~/adafruit-io-node/server/README.md'
        ].join('&&')
      }
    }
  });

  // Default task.
  grunt.registerTask('default', ['shell:node_server', 'replace']);

};
