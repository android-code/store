//use custom PreferenceDataStore inside onCretePreferences
class DataStoreFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //enable data store only for specific preference
            val preference = findPreference("pref2")
            preference.preferenceDataStore = DataStore()

            //ore enable for entire hierarchy
            preferenceManager.preferenceDataStore = DataStore()
        }
    }
}