class LruCacheActivity : AppCompatActivity() {

    //set key and value type, it could be something heavy like Bitmap
    private lateinit var memoryCache : LruCache<String, String>
    private lateinit var diskCache : DiskLruCache //external dependency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init cache before
        initMemoryCache()
        initDiskCache()

        //combine memory and disk cache
        writeToCache("key", "value")
        readFromCache("key")
    }

    fun initMemoryCache() {
        //calculate max memory to 10% of available memory
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

        memoryCache = object: LruCache<String, String>(maxMemory / 10) {
            override fun sizeOf(key: String, value: String): Int {
                return value.toByteArray().size
            }
        }
    }

    fun initDiskCache() {
        val directory = File(cacheDir.path + File.separator + "lru") //use internal or external
        val appVersion = 1 //
        val valueCount = 1 //counter entries for every key
        val maxSize = 1024L * 1024L * 10L //10MB of the cache
        diskCache = DiskLruCache.open(directory, appVersion, valueCount, maxSize)
    }
    
    fun writeToCache(key : String, value : String) {
        if(readFromMemoryCache(key) == null) {
            writeToMemoryCache(key, value)
        }
        writeToDiskCache(key, value)
    }

    fun readFromCache(key : String) : String? {
        val result = readFromMemoryCache(key)
        return if(result == null) {
            readFromDiskCache(key)
        } else result
    }

    fun writeToMemoryCache(key : String, value : String) {
        memoryCache.put(key, value)
    }

    fun readFromMemoryCache(key : String) : String? {
        return memoryCache.get(key)
    }

    fun writeToDiskCache(key : String, value : String) {
        val editor : DiskLruCache.Editor? = diskCache.edit(key) //returns null if there is active editing
        //begin the transaction for this key
        editor?.let {
            //the entry is mark as being edited
            val output = it.newOutputStream(0)
            output.write(value.toByteArray())
            it.commit() //transaction has been finished
        }
    }

    fun readFromDiskCache(key : String) : String? {
        var value : String? = null
        val snapshot : DiskLruCache.Snapshot? = diskCache.get(key) //returns null if entries doesn't exists
        snapshot?.let {
            val input = it.getInputStream(0)
            val bytes = input.readBytes()
            value = String(bytes) //decode bytes
            it.close()
        }
        return value
    }
}