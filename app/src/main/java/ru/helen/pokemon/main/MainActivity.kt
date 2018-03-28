package ru.helen.pokemon.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import ru.helen.pokemon.R
import ru.helen.pokemon.repository.NetworkRepository
import ru.helen.pokemon.repository.NetworkRepositoryImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager.adapter = PageAdapter(getSupportFragmentManager(), this)
        tablayout.setupWithViewPager(viewpager)

        val client : RestClient = RestClient().getInstance()
        val networkRepository : NetworkRepository = NetworkRepositoryImpl(client.getApi())


        networkRepository.getPokemonList(0,20).subscribe({ r ->
          run{
              Log.e("RESPONSE","SUCCESS")
              Log.e("RESPONSECOUNT", r.count.toString())
                    Log.e("RESPONSE", r.result.toString())
          }

        }) { throwable -> Log.e("RESPONSE",throwable.toString())   };

        networkRepository.getPokemon("bulbasaur").subscribe({ r ->
            run{
                Log.e("RESPONSE","SUCCESS")
                Log.e("RESPONSECOUNT", r.stats.toString())
                Log.e("RESPONSE", r.id.toString())
                Log.e("RESPONSE", r.toString())
            }

        }) { throwable -> Log.e("RESPONSE","POKEMON" + throwable.toString())   };

//        rvDiscover.layoutManager = GridLayoutManager(this,2)
//        rvDiscover.setHasFixedSize(true)
    }
}
