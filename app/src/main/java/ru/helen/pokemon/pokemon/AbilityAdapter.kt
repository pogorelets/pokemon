package ru.helen.pokemon.pokemon

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ability_item.view.*
import ru.helen.pokemon.R
import ru.helen.pokemon.model.PokemonAbility

/**
 * Created by lenap on 31.03.2018.
 */
class AbilityAdapter: RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {
    var ability: List<PokemonAbility> = ArrayList()

    fun changedata(ability:List<PokemonAbility>){
        this.ability = ability
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.bind(ability[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
         val layoutInflater = LayoutInflater.from(parent!!.context)
        return ViewHolder(layoutInflater.inflate(R.layout.ability_item, parent, false))
    }

    override fun getItemCount(): Int {
        return ability.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(ability: PokemonAbility) = with(itemView){
            name.text = ability.ability.name
        }

    }
}

