Gatling Feeder

The feeder is a type alias for Iterator[Map[String, T]]. Gatling DSL provides an easy to use ‘feed’ method which takes the feeder as an argument and reads the data from the feeder and injects it into the simulation.

Gatling provided multiple feeders
Character separated file feeders

Gatling provides various ways to read the data from character-separated values files. Now there are multiple use cases where we use different separator characters in our data files like | ‘ : @ # $ <tab> etc.

 So we can use

    val csvFeeder = csv(“userData.csv”) // use a comma separator
    val tsvFeeder = tsv(“userData.tsv”) // use a tabulation separator
    val ssvFeeder = ssv(“userData.ssv”) // use a semicolon separator
    val customSeparatorFeeder = separatedValues(“hello.txt”, ‘#’) // use your own separator.

 If we have large data files then you can use zip file in Gatling feeder and ask Gatling to unzip the data at run time using following syntax

    val csvFeeder = csv(“userData.csv.zip”). unzip 

Feeder Strategies

Till now, we have discussed how to inject data in our requests using feeders but in some scenarios, we have some conditions like I have data in a data file and I want to inject:

    In a sequence 
    Randomly
    As a loop on a batch of data sets like I have only 10 records and want to share the same data between concurrent users.

Gatling DSL handles all these scenarios using different feeder strategies:

    .queue // default behavior: use an Iterator on the underlying sequence
    When using the default queue strategy, make sure that your data set contains enough records. If your feeder runs out of the record, the behavior is undefined and Gatling will forcefully shut down.
    .random // randomly pick an entry in the sequence
    .shuffle // shuffle entries, then behave like queue
    .circular // go back to the top of the sequence once the end is reached


To run the test:-

sbt gatling:test
