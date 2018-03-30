package ru.helen.pokemon.pagefragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_item.view.*
import ru.helen.pokemon.R
import ru.helen.pokemon.model.Pokemon


/**
 * DiscoverAdapter
 */
class DiscoverAdapter(var listener: Contract.ViewPage): RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {
    private var pokemons: List<Pokemon> = ArrayList()

    fun changedata(pokemons:List<Pokemon>){
        this.pokemons = pokemons
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        return ViewHolder(layoutInflater.inflate(R.layout.pokemon_item, parent, false))
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.bind(pokemons[position], listener)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon, listener: Contract.ViewPage) = with(itemView){
            namePokemon.text = pokemon.name

            Glide.with(itemView.context)
                    .load(pokemon.sprites!!.frontDefault)
                    .into(itemView.spriteImage)



        }
    }
}