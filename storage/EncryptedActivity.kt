class EncryptedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        writeEncrypt()
        readEncrypt()
        
        //file is encrypted so reading in standard way returns encrypted content
        val file = File(filesDir, "file.txt")
        file.readBytes().toString(Charset.forName("UTF-8"))
    }

    fun writeEncrypt() {
        val content = "some content"
        val encryptedFile = getEncryptedFile()
        encryptedFile.openFileOutput().apply {
            write(content.toByteArray(Charset.forName("UTF-8")))
            flush()
            close()
        }
    }

    fun readEncrypt() {
        val encryptedFile = getEncryptedFile()
        encryptedFile.openFileInput().apply {
            val byteStream = ByteArrayOutputStream()
            var byte = read()
            while(byte != -1) {
                byteStream.write(byte)
                byte = read()
            }
            val content = byteStream.toByteArray()
            close()
        }
    }
    
    fun getEncryptedFile(): EncryptedFile {
        return EncryptedFile.Builder(
            File(filesDir, "file.txt"),
            this,
            getMasterKeyAlias(),
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
    }

    fun getMasterKeyAlias(): String {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        return MasterKeys.getOrCreate(keyGenParameterSpec)
    }
}