package ru.helen.pokemon.pokemon

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stats_item.view.*
import ru.helen.pokemon.R
import ru.helen.pokemon.model.PokemonStat


/**
 * Created by lenap on 01.04.2018.
 */
class StatsAdapter : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
    var stats: List<PokemonStat> = ArrayList()
    fun changedata(stats: List<PokemonStat>){
        this.stats = stats
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val layoutInflater = LayoutInflater.from(parent!!.context)
        return ViewHolder(layoutInflater.inflate(R.layout.stats_item, parent, false))
    }
    override fun getItemCount(): Int {
        return stats.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.bind(stats[position])
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(stat: PokemonStat) = with(itemView){
            name.text = stat.stat.name + " " + stat.baseStat
        }

    }
}

