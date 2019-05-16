//define methods to use database
@Dao
interface PersonDao {

    //provide query command for @Query annotation
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM person WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Person

    @Query("SELECT * FROM person WHERE age LIKE :age")
    fun findByAge(age: Int): Person

    //do not have to provide any SQL with @Insert
    //when conflict just replace so it works also as update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person : Person)

    @Insert
    fun insertAll(vararg persons: Person)
    
    //do not have to provide any SQL with @Update or @Delete but it can be also replace by normal @Query
    @Update
    fun update(person : Person)

    @Delete
    fun delete(person: Person)
	
    //make transaction with atomic operations
    @Transaction
    fun increaseAgeForAll() {
        for (person in getAll()) {
            person.age = person.age + 1
            update(person)
        }
    }
}