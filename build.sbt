name := "akka-http"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"
lazy val akkaVersion = "2.4.14"

fork in Test := true

libraryDependencies ++= {
  val akkaHttpVersion   = "10.0.4"
  Seq(
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,

    "org.iq80.leveldb" % "leveldb" % "0.7",
    "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
    "commons-io" % "commons-io" % "2.4" % "test",

    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,

    "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
    "org.flywaydb" % "flyway-core" % "3.2.1",

    "de.heikoseeberger" %% "akka-http-circe" % "1.11.0",

    "com.zaxxer" % "HikariCP" % "2.4.5",
    "org.slf4j" % "slf4j-nop" % "1.6.4",

    "io.circe" %% "circe-core" % "0.6.1",
    "io.circe" %% "circe-generic" % "0.6.1",
    "io.circe" %% "circe-parser" % "0.6.1",

    "com.typesafe.slick" %% "slick" % "3.2.0-M2",

    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "test",
    "ru.yandex.qatools.embed" % "postgresql-embedded" % "1.15" % "test"
  )
}
