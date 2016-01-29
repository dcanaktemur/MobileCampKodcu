Unit Testing with an In-Memory Database for a Spring/Hibernate Project
=======

Overview
-------

This project `in-memory-db-test2`:
* puts together the code from article:

>[Unit Testing with an In-Memory Database for a Spring/Hibernate Project](http://whileonefork.blogspot.fr/2012/11/unit-testing-with-in-memory-database.html)

* updates all the frameworks as of their last version, i.e.:

>* Spring 4.1.0.RC2 (°)
>* Hibernate 4.3.6
>* commons-dbcp 2.0.1

* replaces Hibernate API by JPA 2.1

* does some refactoring.


Using Hibernate 4, JPA 2.1 can be specified.

Note (°). In fact Spring 4.1.0.RC2 is required, see:

>[Isolation support for JPA with Hibernate EntityManager 4](https://jira.spring.io/browse/SPR-11942)


Running the sample
-------

You can run the tests from the command line:

		mvn clean test -P no-repo-server