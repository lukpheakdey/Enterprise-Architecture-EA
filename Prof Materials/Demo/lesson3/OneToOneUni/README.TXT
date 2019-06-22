
Exercises One -To One unidirectional
Cascade Persist the Credentials
LAZY load the UserCredentials on FETCH

Demonstrates this by having TWO findOne services

findOne is the regular Entity Manager find will cause Lazy load 
if credentials are referenced

findOneFull   "hydrates" the UserCredentials

ALSO demonstrates Cascade Delete
Deleting member cascades to UserCredentials BUT does NOT cascade to Authority
SO Authority still exists BUT the FK to UserCredentials is cleared

ALSO DEMONSTRATES TESTING
Does same functionality as Project main only using Spring Test Harness
Uses H2 Embedded DB - in-memory DB  [ see test configuration XML]

ALSO demonstrates Dynamic Criteria API query [ Lesson 4]

