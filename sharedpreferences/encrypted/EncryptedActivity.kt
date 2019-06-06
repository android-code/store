class EncryptedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val encryptedSharedPref = createEncryptedSharedPref()
        encryptedSharedPref.edit().putInt("key", 100).commit()
        val encrypted = encryptedSharedPref.getInt("key", 0) //100
		
        //encrypted and standard SharedPreferences are different despite the same file key declaration
        val sharedPref = getSharedPreferences("pl.androidcode.app.FILE_KEY", Context.MODE_PRIVATE)
        val normal = sharedPref.getInt("key", 0) //it will be 0
    }

    fun createEncryptedSharedPref(): SharedPreferences {
        return EncryptedSharedPreferences.create(
            "pl.androidcode.app.FILE_KEY",
            getMasterKeyAlias(),
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun getMasterKeyAlias(): String {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        return MasterKeys.getOrCreate(keyGenParameterSpec)
    }
}