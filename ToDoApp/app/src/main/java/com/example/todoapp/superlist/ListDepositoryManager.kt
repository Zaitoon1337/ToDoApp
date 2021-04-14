import com.example.todoapp.superlist.data.List

class ListDepositoryManager {

    private lateinit var ListCollection: MutableList<List>

    var onList: ((kotlin.collections.List<List>) -> Unit)? = null
    var onListUpdate:((list: List)-> Unit)? = null


    fun load(){
        ListCollection = mutableListOf(
            List("Handle Liste"),
            List("Trening")
        )

        onList?.invoke(ListCollection)
    }

    fun updateList(list: List){
        // Finner bok og erstatter den
        onListUpdate?.invoke(list)
    }

    fun addList(list: List){
        ListCollection.add(list)
        onList?.invoke(ListCollection)
    }

    companion object{
        val instance = ListDepositoryManager()
    }
}