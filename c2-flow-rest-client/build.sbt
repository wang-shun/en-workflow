lazy val root = (project in file(".")).
  settings(
    organization := "com.chinacreator.c2",
    name := "c2-flow-rest-client",
    version := "4.1.6.1-SNAPSHOT",
    scalaVersion := "2.11.4",
    scalacOptions ++= Seq("-feature"),
    javacOptions in compile ++= Seq("-Xlint:deprecation"),
    publishArtifact in (Compile, packageDoc) := false,
    resolvers += Resolver.mavenLocal,
    libraryDependencies ++= Seq(
      "io.swagger" % "swagger-annotations" % "1.5.8",
      "org.glassfish.jersey.core" % "jersey-client" % "2.22.2",
      "org.glassfish.jersey.media" % "jersey-media-multipart" % "2.22.2",
      "org.glassfish.jersey.media" % "jersey-media-json-jackson" % "2.22.2",
      "com.fasterxml.jackson.core" % "jackson-core" % "2.7.5",
      "com.fasterxml.jackson.core" % "jackson-annotations" % "2.7.5",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.5",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.7.5",
      "joda-time" % "joda-time" % "2.9.4",
      "com.brsanthu" % "migbase64" % "2.2",
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.10" % "test"
    )
  )