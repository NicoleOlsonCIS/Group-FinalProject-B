# Group-FinalProject-B
For collaboration on completing Final Project Part B with group members


DONE: 

testScheme()
    Unit Test on Scheme for two different settings (1. only http, https, ftp allowed, 2. all schemes allowed)

testAuthority()
    Unit Test on Authority 

testRandomPorts()
    Random Test of 2000 Valid Ports and 2000 Invalid Ports

testRandomIP()
    Random Test of IP address (2000 valid ipv4, 2000 invalid ipv4, 1000 valid ipv6, 1000 valid ipv6)

Printing functions (class teststruct) 

File I/O functions (class teststruct)

TO DO:

Manual Testing could be written again, the one there was just a draft to get going with the code ... (~1-hours)

testPath()
    Unit test of Path(note 2 options settings - allow two slashes and fragments ... so should be done like the scheme testing)

testQuery()
    Unit test of Query (very very basic)

Other tests? I don't know ... 

Then after those tests are in place, and all our tests pass, we can introduce bugs and then have something to write about ... 

The write up: Shouldn't take too long


BUGS IN TEST CODE:

-the Valid IPV6 in testRandomIP() are coming out invalid, so I have to fix something there

-there are some authority tests failing that shouldn't be, I don't think, they are printing to console, I will look into them





