package com.insight.model

/*To construct the data, seven geometric parameters of wheat kernels were measured:
1. area A,
2. perimeter P,
3. compactness C = 4*pi*A/P^2,
4. length of kernel,
5. width of kernel,
6. asymmetry coefficient
7. length of kernel groove.
All of these parameters were real-valued continuous.
*/

case class WheatFeature(area: Double, perimeter: Double, compactness: Double,kernelLen: Double,kernelWidth:Double,aCoefficient: Double,grooveLen: Double,label:Double)

