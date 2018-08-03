# Khiva-Kotlin

[![License: MPL 2.0](https://img.shields.io/badge/License-MPL%202.0-brightgreen.svg)](https://github.com/shapelets/khiva-kotlin/blob/master/LICENSE)  

| Build Linux and Mac OS                                                                                                                   |  Build Windows                                                                                                                                                               | Code Coverage                                                                                                                                              |
|:----------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------:|
| [![Build Status](https://travis-ci.org/shapelets/khiva-kotlin.svg?branch=master)](https://travis-ci.org/shapelets/khiva-kotlin/branches) | [![Build status](https://ci.appveyor.com/api/projects/status/vwgmj2ij3ng3eb1w/branch/master?svg=true)](https://ci.appveyor.com/project/shapelets/khiva-kotlin/branch/master) |[![Coverage Status](https://codecov.io/gh/shapelets/khiva-kotlin/branch/master/graph/badge.svg)](https://codecov.io/gh/shapelets/khiva-kotlin/branch/master)|

# README #
This is the Khiva binding for Kotlin, it allows the usage of Khiva library from Kotlin.

## License
This project is licensed under [MPL-v2](https://www.mozilla.org/en-US/MPL/2.0/).
 
## Quick Summary
This Kotlin package called 'khiva' provides all the functionalities of the Khiva library for time series analytics.

## Requirements
* Arrayfire
* Khiva library

## Set up
It is just needed to execute the next command in the root directory of the project:
```bash
mvn install
```

## Executing the tests:
Execute the next command in the root directory of the project:
```bash
 mvn test
```
 
Note: The tests are executed automatically when the package is installed.

## Documentation
This Kotlin package follows the standard way of writing documentation of Kotlin code using [dokka](https://github.com/Kotlin/dokka).

In order to generate the documentation, execute the next command in the root directory of the project: 
```bash
mvn dokka:dokka
```

You can also generate the regular javadoc style documentation with:
```bash
mvn dokka:javadoc
```

Dokka does not support the writing of mathematical formulas, therefore functions containing them will show their LaTeX original text version. This will be fixed as soon as they support it.

## Contributing
The rules to contribute to this project are described [here](CONTRIBUTING.md)

[![Powered by Shapelets](https://img.shields.io/badge/powered%20by-Shapelets-orange.svg?style=flat&colorA=E1523D&colorB=007D8A)](https://shapelets.io)