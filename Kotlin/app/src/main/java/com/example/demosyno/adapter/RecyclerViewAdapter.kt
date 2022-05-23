package com.example.demosyno.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demosyno.R
import com.example.demosyno.model.CountryItem

class RecyclerViewAdapter(private val listener : OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    private var listDate: MutableList<CountryItem>? = null
    fun setListData(listData: MutableList<CountryItem>?){
        this.listDate = listData
    }
    fun getListData(): MutableList<CountryItem>? {
        return listDate
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(listDate?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listDate == null ) return 0
        else
            return listDate?.size!!
    }
   inner class MyViewHolder(view : View):RecyclerView.ViewHolder(view),
    View.OnClickListener{
        val image: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.country_name)
        fun bind(data: CountryItem) {
            name.text = data.country
            Glide.with(image)
                .load(data.countryInfo.flag)
                .into(image)
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position:Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, name.text as String)
            }
        }
    }
    interface  OnItemClickListener {
        fun onItemClick(position: Int,name:String)
    }
}