name := "ScalaDataAnalysis"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies  ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "1.1",
  
  // Native libraries are not included by default. add this if you want them
  // Native libraries greatly improve performance, but increase jar sizes. 
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "1.1",
  
  // The visualization library is distributed separately as well.
  // It depends on LGPL code
  "org.scalanlp" %% "breeze-viz" % "1.1",
  "org.apache.commons" % "commons-math3" % "3.6"
)
// libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "4.2.0"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "4.2.0" classifier "models-chinese"
// libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "4.2.0" classifier "models-english"

//libraryDependencies += "org.jzy3d" % "jzy3d" % "0.9" from "http://www.jzy3d.org/release/0.9a3/org.jzy3d-0.9.jar"
//
//addZipJar("org.jzy3d" % "jzy3d-deps" % "0.9" from "http://www.jzy3d.org/release/0.9/org.jzy3d-0.9-dependencies.zip", Compile)
//{
//  val arch = "macosx" // "windows-amd64" "windows-i586" "linux-amd64" "linux-i586"
//  addZipJar("org.jzy3d" % "jzy3d-native" % "0.9" from "http://www.jzy3d.org/release/0.9/org.jzy3d-0.9-binaries-%s.zip".format(arch), Compile)
//}


resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"
resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.3")