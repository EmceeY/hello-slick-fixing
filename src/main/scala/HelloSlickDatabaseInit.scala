import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import slick.backend.DatabasePublisher
import slick.driver.PostgresDriver.api._
import com.EmceeY.secrets.Secrets

object HelloSlickDatabaseInit extends App {
    val YOUR_POSTGRESQL_PASSWORD_HERE = Secrets.YOURPASSHERE
    val url = "jdbc:postgresql://localhost/coffeeDB?user=postgres&password=" + YOUR_POSTGRESQL_PASSWORD_HERE
    val db = Database.forURL(url, driver = "org.postgresql.Driver")

  try {

      // The query interface for the Suppliers table
      val suppliers: TableQuery[Suppliers] = TableQuery[Suppliers]

      // the query interface for the Coffees table
      val coffees: TableQuery[Coffees] = TableQuery[Coffees]

      val setupAction: DBIO[Unit] = DBIO.seq(
        // Create the schema by combining the DDLs for the Suppliers and Coffees
        // tables using the query interfaces
        (suppliers.schema ++ coffees.schema).create,

        // Insert some suppliers
        suppliers += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
        suppliers += ( 49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
        suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
     )

    val setupFuture: Future[Unit] = db.run(setupAction)

    val f = setupFuture.flatMap { _ =>

      //Insert some coffees (using JDBC's batch insert feature)
      val insertAction: DBIO[Option[Int]] = coffees ++= Seq (
        ("Colombian",         101, 7.99, 0, 0),
        ("French_Roast",       49, 8.99, 0, 0),
        ("Espresso",          150, 9.99, 0, 0),
        ("Colombian_Decaf",   101, 8.99, 0, 0),
        ("French_Roast_Decaf", 49, 9.99, 0, 0)
      )

      val insertAndPrintAction: DBIO[Unit] = insertAction.map { coffeesInsertResult =>
        // Print the number of rows inserted
        coffeesInsertResult foreach { numRows =>
          println(s"Inserted $numRows rows into the Coffees table")
        }
      }

      val allSuppliersAction: DBIO[Seq[(Int, String, String, String, String, String)]] =
        suppliers.result

      val combinedAction: DBIO[Seq[(Int, String, String, String, String, String)]] =
        insertAndPrintAction >> allSuppliersAction

      val combinedFuture: Future[Seq[(Int, String, String, String, String, String)]] =
        db.run(combinedAction)

      combinedFuture.map { allSuppliers =>
        allSuppliers.foreach(println)
      }

    }
    Await.result(f, Duration.Inf)

} finally db.close
}
