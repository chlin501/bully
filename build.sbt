name := "bully"

version := "0.1"

scalaVersion := "2.13.5"

val grpcVersion = "1.31.1"

PB.targets in Compile := Seq(
  scalapb.gen(grpc = true) -> (sourceManaged in Compile).value,
  scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value
)

libraryDependencies ++= Seq(
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "io.grpc" % "grpc-netty" % grpcVersion,
  "dev.zio" %% "zio" % "1.0.4-2",
  "org.scalatest" %% "scalatest" % "3.2.5" % "test"
)
