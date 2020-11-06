package walter.com.tottustest.infraestructure.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
abstract class BaseRecycler<T>: RecyclerView.Adapter<BaseRecycler.MyViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<T>
    var list: List<T> = listOf()

    abstract fun getLayout(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(getLayout(), parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        onBindViewHold(position, holder.itemView)
        holder.itemView.setOnClickListener { onClick(holder.itemView, position, list[position]) }
    }

    abstract fun onBindViewHold(position: Int, itemView: View)

    abstract fun onClick(itemView: View, position: Int, data: T)

    override fun getItemCount(): Int = list.size

    inline fun setOnClickListener(crossinline body: (Int, T, View) -> Unit = { _, _, _ ->}) {
        itemClickListener = object: ItemClickListener<T> {
            override fun onClick(position: Int, data: T, view: View) {
                body(position, data, view)
            }

        }
    }

    fun updateList(items: List<T>) {
        list = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}