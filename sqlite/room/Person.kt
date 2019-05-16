//represents some table of database, define scheme here
@Entity(tableName = "person") //by default tableName is class name
data class Person 
(
    @ColumnInfo(name = "name")
    var firstName: String,
    var age: Int //by default column name is field name	    
) 
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}