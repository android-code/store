class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
	
    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return when (preference?.key) {
            "pref2" -> {
                //do something when pref has changed if needed
                true
            }
            else -> {
                super.onPreferenceTreeClick(preference)
            }
        }
    }
	
    //implement more methods to react for interactions
}