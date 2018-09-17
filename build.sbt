libraryDependencies ++= List(
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",

  // Database
  "com.h2database" % "h2" % "1.4.187",
  "org.postgresql" % "postgresql" % "9.4.1208",

  //Test
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
