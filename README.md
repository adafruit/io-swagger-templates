# Adafruit IO Swagger Codegen Templates
This repository contains [Swagger Codegen][1] templates. The templates are used to generate the [open source Adafruit IO server][3] and clients from the [Adafruit IO Swagger REST documentation][2].

## Build Instructions

Java v1.7
```console
$ java -version
java version "1.7.0_75"
Java(TM) SE Runtime Environment (build 1.7.0_75-b13)
Java HotSpot(TM) 64-Bit Server VM (build 24.75-b04, mixed mode)
```
Apache Maven v3.3.3
```console
$ mvn -version
Apache Maven 3.3.3 (7994120775791599e205a5524ec3e0dfe41d4a06; 2015-04-22T07:57:37-04:00)
Maven home: /usr/local/Cellar/maven/3.3.3/libexec
Java version: 1.7.0_75, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.7.0_75.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.10.4", arch: "x86_64", family: "mac"
```
Node.js v0.12.0 or higher
```console
$ node -v
v4.1.0
```
Clone this repository:
```console
$ git clone --recursive https://github.com/adafruit/io-swagger-templates && cd io-swagger-templates
```

Install dependencies:
```console
$ cd swagger-codegen && mvn package && cd ..
$ npm install -g grunt-cli
$ npm install
```

Build:
```console
$ grunt
```

## Contributing
1. Fork and clone your copy of this repository
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push -u origin my-new-feature`)
5. Create new Pull Request on GitHub with a detailed description of the changes

In lieu of a formal styleguide, take care to maintain the existing coding style.

## License
Copyright (c) 2015 Adafruit Industries. Licensed under the MIT license.

Adafruit invests time and resources providing this open source code. Please support Adafruit and open-source hardware by purchasing products from [Adafruit](https://adafruit.com).

[1]: https://github.com/swagger-api/swagger-codegen
[2]: https://io.adafruit.com/api/docs/
[3]: https://github.com/adafruit/adafruit-io-node
