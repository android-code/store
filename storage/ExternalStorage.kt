class ExternalStorage {

    fun doSomethingWithFile() {
        if(hasPermissions()) {
            //write or read to file depends on needs
            if(isWriteable()) {
                val file = getPrivateDirectory()
                //write to file
            }
            else if(isReadable()) {
                //get file and read it
            }
        }
    }

    //stay after uninstall, available for other apps
    fun getPublicDirectoryFile() : File? {
        //use one of Environment folder or pass null to get the root
        val file = File(Environment.getExternalStoragePublicDirectory(null), "public.txt")
        if(file.mkdirs()) {
            //file has not been created
        }
        return file
    }

    //remove after uninstall, not visible for MediaStore
    fun getPrivateDirectoryFile() : File? {
        //use one of Environment folder or pass null to get the root
        val file = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "private.txt")
        if(file.mkdirs()) {
            //file has not been created
        }
        return file
    }

    //there can be more than one external storage, check it
    fun listExternalDirectories() {
        val list = getExternalFilesDirs(null)
        if(list[0] != null) {
            //primary external storage exists
        }
        if(list[1] != null) {
            //secondary external storage exists (it should be removable storage like micro SD)
        }
        //in most cases there are no more than two directories
    }

    //it depends on API level if they are needed or not
    fun hasPermissions() : Boolean {
        //check if the app has READ_EXTERNAL_STORAGE or WRITE_EXTERNAL_STORAGE provided
        return true
    }

    //check is storage writable or readable before do something
    fun isWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun isReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }
}