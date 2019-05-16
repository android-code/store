//use SQLiteDatabase in some client
class SQLiteActivity : AppCompatActivity() {
    
    private lateinit var database : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get instance of writable or readable database to reuse it in multiple queries
        database = Database(this).writableDatabase
        //use database by defined methods below
    }

    override fun onDestroy() {
        //close costly database connection
        database.close()
        super.onDestroy()
    }
	
    //some database manage methods, there could be inside some manager with conversion some object type into raw data
    //notice how many lines of code are needed to do simple single query operation

    fun put() {
        //prepare data
        val values = ContentValues()
        values.put(DatabaseContract.Person.COLUMN_NAME, "Jack")
        values.put(DatabaseContract.Person.COLUMN_AGE, 50)

        //put the data and get id of new entry, returns -1 if fails
        val id = database.insert(DatabaseContract.Entry.TABLE_PERSON, null, values)
    }

    fun read() {
        //define the query params
        val table = DatabaseContract.Person.TABLE_PERSON
        val columns : Array<String>? = null //get all columns
        val selection = DatabaseContract.Person.COLUMN_NAME + " = ?" //columns for WHERE
        val args = arrayOf("Jack") //values for WHERE
        val groupBy = null //ignore
        val filterBy = null //ignore
        val sortOrder = DatabaseContract.Person.COLUMN_AGE + " DESC"

        //make query and read data from the cursor by iterator methods
        val cursor : Cursor = database.query(table, columns, selection, args, groupBy, filterBy, sortOrder)
        while (cursor.moveToNext()) {
            val age = cursor.getString(cursor.getColumnIndex(DatabaseContract.Person.COLUMN_AGE))
            //do something with item
        }
        cursor.close()
    }

    fun delete() {
        //define query params
        val table = DatabaseContract.Person.TABLE_PERSON
        val selection = DatabaseContract.Person.COLUMN_NAME + " LIKE ?"
        val args = arrayOf("Jack")

        //delete entries and get number of the removed items
        val count = database.delete(table, selection, args)
    }

    fun update() { 
        //prepare data
        val values = ContentValues()
        values.put(DatabaseContract.Person.COLUMN_AGE, 51)
        
        //define query params
        val table = DatabaseContract.Person.TABLE_PERSON
        val selection = DatabaseContract.Person.COLUMN_NAME + " LIKE ?"
        val args = arrayOf("Jack")
        
        //update entries and updated count
        val count = database.update(table, values, selection, args)
    }
}