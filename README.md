This was a template for [Typesafe Activator](http://typesafe.com/platform/getstarted).

However, Typesafe Activator was taken offline in 2017.

Therefore, this template is being brought up to date as of Slick 3.2.3 as a learning exercise.

If you somehow stumbled across this repo, I hope you find it useful!

All goodness credit to Lightbend, faults are my own.

Fixed the issues present with out of date versions
Commented out in memory database

Connected postgresql database that was created locally to reflect the example schema

None of the below SQL is necessary!


Once a connection is established with the DB,
run HelloSlickDatabaseInit tables and data will be added for you.

SQL code to generate tables (happens automagically):

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

SQL code to insert data into tables (happens automatically):

    Supplier Data:
        INSERT INTO suppliers (sup_id, sup_name, street, city, state, zip) VALUES
        (101, 'Acme, Inc.', '99 Market Street', 'Groundsville', 'CA', '95199'),
        ( 49, 'Superior Coffee', '1 Party Place', 'Mendocino', 'CA', '95460'),
        (150, 'The High Ground', '100 Coffee Lane', 'Meadows', 'CA', '93966');

    Coffee Data:
        INSERT INTO coffees (cof_name, sup_id, price, sales, total) VALUES
        ('Colombian',         101, 7.99, 0, 0),
        ('French_Roast',       49, 8.99, 0, 0),
        ('Espresso',          150, 9.99, 0, 0),
        ('Colombian_Decaf',   101, 8.99, 0, 0),
        ('French_Roast_Decaf', 49, 9.99, 0, 0);
