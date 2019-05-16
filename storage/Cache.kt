class Cache {

    fun readCacheFile() {
        //from getCacheDir
        val file = File(getCacheDir(), "cache.txt")
        //read cache, but be aware if still exists
    }
    
    fun writeCacheFile() {
        val file = File.createTempFile("cache", "txt", getCacheDir())
        //write cache, but be aware if still exists
        //e.g. convert object collection to json
    }
}