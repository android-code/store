//extend SQLiteOpenHelper
class Database(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Person.db"
    }

    //define SQL queries, use DatabaseContract
    private val SQL_CREATE =
        "CREATE TABLE ${DatabaseContract.Person.TABLE_PERSON} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${DatabaseContract.Person.COLUMN_NAME} TEXT," +
            "${DatabaseContract.Person.COLUMN_AGE} INTEGER)"

    private val SQL_DELETE =
        "DROP TABLE IF EXISTS ${DatabaseContract.Person.TABLE_PERSON}"

    override fun onCreate(db: SQLiteDatabase?) {
        //just create database with specific structure
        db?.execSQL(SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //do something when database structure has updated, e.g. clear all old data
        db?.execSQL(SQL_DELETE)
        onCreate(db)
    }

    //implement other methods like onDowngrade, onConfigure
}

//good practice is to define database scheme contract to simpler manage it
object DatabaseContract {

    //create inner class for each Table, BaseColumns has primary key field called _ID
    object Person : BaseColumns {
        const val TABLE_PERSON = "person"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
    }
}