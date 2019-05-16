class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
 
    fun getSharedPreferences(name : String) : SharedPreferences {
        //must be called on Context
        return getSharedPreferences(name, Context.MODE_PRIVATE) //pass public to allow access outside app
    }

    fun getSharedPreferencesForActivity() : SharedPreferences {
        //SharedPreferences file only for this activity
        return getPreferences(Context.MODE_PRIVATE) 
    }

    fun write(key : String, value : Int) {
        val sharedPref = getSharedPreferences("pl.androidcode.app.FILE_KEY")
        val editor = sharedPref.edit()
		
        //do some transactions
        editor.putInt(key, value) //or use another method to put different types
        editor.commit() //to save immediately or apply to save in background
    }
    
    fun read(key : String) : Int {
        val sharedPref = getSharedPreferences("pl.androidcode.app.FILE_KEY")
        val default = 0
        return sharedPref.getInt(key, default)
    }
}