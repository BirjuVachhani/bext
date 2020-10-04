# Bext - Base Extensions

[![License](https://img.shields.io/badge/License-Apache%202.0-2196F3.svg?style=for-the-badge)](https://opensource.org/licenses/Apache-2.0)
[![language](https://img.shields.io/github/languages/top/BirjuVachhani/bext.svg?style=for-the-badge&colorB=f18e33)](https://kotlinlang.org/)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg?style=for-the-badge)](https://www.android.com/)
[![API](https://img.shields.io/badge/API-16%2B-F44336.svg?style=for-the-badge)](https://android-arsenal.com/api?level=16)
[![Release](https://jitpack.io/v/BirjuVachhani/locus-android.svg?style=flat-square)](https://jitpack.io/BirjuVachhani/locus-android)

Bext is a kotlin extensions library which makes it very easy to write short and clean code and saves a developer's life from being miserable.

See [wiki](https://github.com/BirjuVachhani/bext/wiki) for more information on extensions and packages.

Please note that from the version 2.0.0, this library is migrated to **AndroidX** and doesn't provide support for older Android packages.


## Gradle Dependency

* Add the JitPack repository to your **project's build.gradle** file.

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

* Add the dependency in your **app's build.gradle** file.

```
dependencies {
    // Core extensions from standard android packages
    implementation "com.github.BirjuVachhani.bext:bext-core:latest-version"
    
    // Extensions for Android Architecture components
    implementation "com.github.BirjuVachhani.bext:bext-arch:latest-version"
    
    // Extensions for common third-party libraries
    implementation "com.github.BirjuVachhani.bext:bext-common:latest-version"
    
    // includes all the extensions
    implementation "com.github.BirjuVachhani.bext:bext-all:latest-version"
}
```
see [wiki](https://github.com/Birjuvachhani/bext/wiki) for more information.


## Contribution

You are most welcome to contribute to this project!

Please have a look at [Contributing Guidelines](https://github.com/BirjuVachhani/bext/blob/master/CONTRIBUTING.md), before contributing and proposing a change.

<a href="https://www.buymeacoffee.com/birjuvachhani" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-blue.png" alt="Buy Me A Coffee" style="height: 51px !important;width: 217px !important;" ></a>


## Licence

```
Copyright 2020 BirjuVachhani

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
