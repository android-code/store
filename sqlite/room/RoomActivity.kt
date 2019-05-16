class RoomActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    //create or inject instance, it could be Singleton
    lateinit var database : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room
            .databaseBuilder(this, AppDatabase::class.java, "RoomDatabase")
            .build()

        doSomeOperations()
    }

    fun doSomeOperations() {
        //must be run on the background thread otherwise exception will be thrown
        launch(Dispatchers.IO) {
            //use it by Dao instance
            val personDao = database.personDao()

            //CREATE objects
            personDao.insert(Person("Jack", 50))
            personDao.insertAll(Person("Johhnie", 60), Person("William", 70))

            //SELECT objects
            var persons = personDao.getAll()
            val person = personDao.findByName("Jack")

            //UPDATE object
            person.age = 20
            personDao.update(person)

            //DELETE object
            personDao.delete(person)
            
            //run transaction method
            personDao.increaseAgeForAll()
        }
    }
}