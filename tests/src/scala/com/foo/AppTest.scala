package no.krined.noplan

import weaver.*

object ExampleTestSuite extends SimpleIOSuite:
  pureTest("I SUCCEED"):
    expect("true" == "true")
