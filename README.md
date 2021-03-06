
[ ![Download](https://api.bintray.com/packages/lehen01/maven/LocationProgressBar/images/download.svg) ](https://bintray.com/lehen01/maven/LocationProgressBar/_latestVersion) [![Build Status](https://travis-ci.org/leandroBorgesFerreira/LocationProgressBar.svg?branch=master)](https://travis-ci.org/leandroBorgesFerreira/LocationProgressBar)
## Location Progress Bar
Custom Android Progress Bar with some animations

.![enter image description here](https://lh3.googleusercontent.com/-kVgciOL3a-I/WAormd0DClI/AAAAAAAAKUs/FgsO51cMx2Q34TSw0c7vyZ6EyRsWNLLRgCLcB/s0/loadingprogressbar.gif "loadingprogressbar.gif")

##Usage

    LocationProgressBar.AnimationConfig animationConfig = new LocationProgressBar.AnimationConfig(
                0, //From progress
                100, //To progress
                800, // Duration
                600); //Start delay

        locationProgressBar.configAnimation(animationConfig);
        locationProgressBar.animateProgress();

##Install

    compile 'br.com.simplepass:location-progress-bar:0.8.2'

##License

The MIT License (MIT)

Copyright (c) 2016 Leandro Ferreira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.