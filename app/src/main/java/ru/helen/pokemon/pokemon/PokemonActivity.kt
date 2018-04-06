package ru.helen.pokemon.pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pokemon.*
import ru.helen.pokemon.App
import ru.helen.pokemon.R

import ru.helen.pokemon.model.Pokemon
import javax.inject.Inject

class PokemonActivity : AppCompatActivity(), Contract.ViewPokemon {


    @Inject
    lateinit var presenter: Presenter
    var adapter: AbilityAdapter = AbilityAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        App.instance.initPokemonComponent(this).inject(this)
        presenter.checkSave(intent.getIntExtra("id",1))
    }

    override fun initView(pokemon: Pokemon) {
        Glide.with(this)
                .load(pokemon.sprites!!.frontDefault)
                .into(sprite)
        pokemonName.text = pokemon.name
        val adapterstats = StatsAdapter()
        rvstats.layoutManager = LinearLayoutManager(this)
        rvstats.setHasFixedSize(true)
        rvstats.adapter = adapterstats
        adapterstats.changedata(pokemon.stats!!)
        rvability.layoutManager = LinearLayoutManager(this)
        rvability.setHasFixedSize(true)
        rvability.adapter = adapter
        adapter.changedata(pokemon.abilities!!)
        lstats.visibility = View.VISIBLE
        lability.visibility = View.VISIBLE
        btnSave.setOnClickListener {presenter.savePokemon(pokemon)}
        btnDelete.setOnClickListener { presenter.deletePokemon(pokemon.id!!) }
    }

    override fun showprogress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun dismissprogress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showError(error: String) {
        Log.e("ERROR", error)
    }


    override fun btnSaveVisible() {
        btnSave.visibility = View.VISIBLE
        btnDelete.visibility = View.GONE
    }

    override fun btnSaveInvisible() {
        btnSave.visibility = View.GONE
        btnDelete.visibility = View.VISIBLE
    }

    override fun back() {
        super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        App.instance.destroyPokemonComponent()

    }
}
