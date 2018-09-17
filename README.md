This was a template for [Typesafe Activator](http://typesafe.com/platform/getstarted).

However, Typesafe Activator was taken offline in 2017.

Therefore, this template is being brought up to date as of Slick 3.2.3 as a learning exercise.

If you somehow stumbled across this repo, I hope you find it useful!

All goodness credit to Lightbend, faults are my own.

Fixed the issues present with out of date versions
Commented out in memory database

Connected postgresql database that was created locally to reflect the example schema
    needed both coffee and supplier tables

SQL code to generate tables:
    COFFEES Table:
        CREATE TABLE COFFEES
        (
        COF_NAME text NOT NULL,
        SUP_ID integer NOT NULL,
        PRICE real NOT NULL,
        SALES integer,
        TOTAL integer,
        CONSTRAINT pk PRIMARY KEY (COF_NAME)
        )
    SUPPLIERS Table:
        CREATE TABLE SUPPLIERS
        (
        SUP_ID integer NOT NULL,
        SUP_NAME text NOT NULL,
        STREET text,
        CITY text,
        STATE text,
        ZIP text,
        CONSTRAINT sk PRIMARY KEY (SUP_ID)
        )

SQL code to insert data into tables:
