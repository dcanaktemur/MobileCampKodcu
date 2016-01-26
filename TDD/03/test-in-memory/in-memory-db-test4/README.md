Unit Testing with an In-Memory Database for a Spring/Hibernate Project
=======

Overview
-------

This project `in-memory-db-test4`:

* puts together the code from article:

>[Unit Testing with an In-Memory Database for a Spring/Hibernate Project](http://whileonefork.blogspot.fr/2012/11/unit-testing-with-in-memory-database.html)

* updates all the frameworks as of their last version, i.e.:

>* Spring 4.1.0.RC2 (°)
>* Hibernate 4.3.6
>* commons-dbcp 2.0.1

* replaces Hibernate API by JPA 2.1

* replaces XML Spring configuration with a (almost) pure java one

* updates logging (slf4j with logback)

* uses Spring DataSourceInitializer to populate the database with the same data before each test (°°).



Using Hibernate 4, JPA 2.1 can be specified.

Notes

(°) In fact Spring 4.1.0.RC2 is required, see:

>[Isolation support for JPA with Hibernate EntityManager 4](https://jira.spring.io/browse/SPR-11942)

(°°) And drops Hibernate SchemaExport

Running the sample
-------

You can run the tests:

* from the command line with

		mvn clean test -P no-repo-server
		
* under Eclipse, select the project then launch the configuration
        
		in-memory-db-test4_clean_test.launch
