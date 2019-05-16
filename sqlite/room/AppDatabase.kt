//serves as the access point, define list of entities associated with database
@Database(entities = arrayOf(Person::class), version = 1)
//must be abstract and extends RoomDatabase and marked as Database
abstract class AppDatabase : RoomDatabase() {

    //abstract no arg methods with returns of entities
    abstract fun personDao() : PersonDao
    //define more methods for other entities class
}