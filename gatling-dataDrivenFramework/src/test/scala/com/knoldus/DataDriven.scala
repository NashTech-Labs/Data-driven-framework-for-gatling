package com.knoldus

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class DataDriven extends Simulation {

  val filePath = "/home/knoldus/GatlingCourse/src/test/resources/data/state.csv"

  val httpProtocol: HttpProtocolBuilder = http.baseUrl("https://api.openbrewerydb.org")
  val state = csv(filePath).circular // reads data from csv file .
  val scn = scenario("anything")
    .feed(state) // feeds the csv file into the simulation
    .exec(http("Course Request")
      .get("/breweries")
      .check(bodyString.saveAs("my Response")))
    .exec{session =>
      val a = session("my Response").as[String]
      print(a)
      session}

  setUp(scn.inject(atOnceUsers(4)).protocols(httpProtocol))
}
