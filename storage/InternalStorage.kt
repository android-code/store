class InternalStorage {

    fun readFile() {
        //represents file system associated with the app
        val dir = getFilesDir() //the name of directory is app package
        val file = File(dir, "file.txt")
        //do something with the file
    }

    fun writeFile() {
        val content = "some file content"

        //open file by constructor or by stream and write to it
        //use MODE_PRIVATE to make it private for only this app
        openFileOutput("file.txt", Context.MODE_PRIVATE).use {
            //write data content
            it.write(content.toByteArray())
        }

        //to make it public use FileProvider instead of MODE_WORLD_READABLE
    }

    fun deleteFile() {
        val file = File(filesDir, "file.txt")
        if(file.exists()) {
            file.delete()
        }
    }
    
    fun checkSpaceInStorage() {
        val total = filesDir.totalSpace
        val free = filesDir.freeSpace
    }

    fun listFilesInStorage() {
        val list = fileList()
        //do something with files names
    }
	
    //do more things with File class
}