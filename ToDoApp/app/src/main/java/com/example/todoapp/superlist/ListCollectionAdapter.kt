import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.superlist.data.List
import com.example.todoapp.databinding.ListLayoutBinding

class ListCollectionAdapter(private var lists: kotlin.collections.List<List>, private val onListClicked:(List) -> Unit) : RecyclerView.Adapter<ListCollectionAdapter.ViewHolder>(){

    class ViewHolder(val binding:ListLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(list: List, onListClicked:(List) -> Unit) {
            binding.toDo.text  = list.title
            binding.card.setOnClickListener{
                onListClicked(list)
            }
        }
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list =lists[position]
        holder.bind(list, onListClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    public fun  updateCollection(newLists: kotlin.collections.List<List>){
        lists = newLists
        notifyDataSetChanged()
    }
}