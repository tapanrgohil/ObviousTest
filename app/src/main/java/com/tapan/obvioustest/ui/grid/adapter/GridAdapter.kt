package com.tapan.obvioustest.ui.grid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tapan.obvioustest.R
import com.tapan.obvioustest.ui.grid.model.ImageModel
import kotlinx.android.synthetic.main.item_grid.view.*

class GridAdapter(private val list: ArrayList<ImageModel> = arrayListOf()) :
    RecyclerView.Adapter<GridAdapter.GridViewHolder>() {


    class GridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(imageModel: ImageModel) {
            itemView.apply {
                image.load(imageModel.image) {
                    error(android.R.drawable.ic_menu_close_clear_cancel)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return with(LayoutInflater.from(parent.context)) {
            GridViewHolder(inflate(R.layout.item_grid, parent, false))
        }
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateDataSet(list: List<ImageModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


}