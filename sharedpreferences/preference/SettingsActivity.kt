class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //add Preference Fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SettingsFragment())
            .commit()
    }
	
    //read some pref when needed, use getDefaultSharedPreferences
    fun readPref() : Boolean {
        val sharedPref = getDefaultSharedPreferences(this)
        return sharedPref.getBoolean("pref2", false)
    }
}