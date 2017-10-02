lazy val commonSettings = Seq(
  name := "numberwang-akka-http",
  organization := "xyz.casperkoning",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.12.3"
)

lazy val app = (project in file(".")).enablePlugins(JavaAppPackaging)
  .settings(commonSettings: _*)
  .settings( // heroku settings
    herokuAppName in Compile := sys.props.getOrElse("heroku_name", "secret"),
    herokuJdkVersion in Compile := "1.8",
    herokuConfigVars in Compile := Map(
    ),
    herokuProcessTypes in Compile := Map(
      "web" -> s"target/universal/stage/bin/numberwang-akka-http -Dhttp.port=$${PORT}"
    )
  )


libraryDependencies ++= {
  // compile
  val akkaHttp          = "com.typesafe.akka" %% "akka-http"            % "10.0.10"
  val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10"

  // test
  val akkaHttpTestKit   = "com.typesafe.akka" %% "akka-http-testkit"    % "10.0.10" % Test

  // runtime
  val akkaSlf4j         = "com.typesafe.akka" %% "akka-slf4j"           % "2.4.19" % Runtime
  val logback           = "ch.qos.logback"    %  "logback-classic"      % "1.2.3" % Runtime

  Seq(
    akkaHttp,
    akkaHttpSprayJson,
    akkaHttpTestKit,
    akkaSlf4j,
    logback
  )
}
