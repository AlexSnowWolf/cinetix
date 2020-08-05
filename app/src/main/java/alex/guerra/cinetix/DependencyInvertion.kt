package alex.guerra.cinetix

class Item

interface Database {
    fun requestItems(): List<Item>
}

class RoomDatabase() : Database {
    override fun requestItems(): List<Item> {
        TODO("Not yet implemented")
    }
}

class DataRepository(private val database: Database) {
    fun requestItems() = database.requestItems()
}
