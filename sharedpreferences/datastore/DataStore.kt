//override only used operations
@RequiresApi(Build.VERSION_CODES.O)
class DataStore : PreferenceDataStore {
    
    //make sure to provide proper non blocking threading during extra work
    
    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        //try to get pref from remote
        //if failed or doesn't exists then try to get from local
        return super.getBoolean(key, defValue)
        //log operation
    }

    override fun putBoolean(key: String?, value: Boolean) {
        //try to put pref to remote
        //save also in local
        super.putBoolean(key, value)
        //log operation
    }
}