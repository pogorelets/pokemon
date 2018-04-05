package ru.helen.pokemon.pagefragment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_page.*
import ru.helen.pokemon.App
import ru.helen.pokemon.R
import javax.inject.Inject
import android.support.v7.widget.RecyclerView
import ru.helen.pokemon.model.Pokemons
import ru.helen.pokemon.pokemon.PokemonActivity


class PageFragment : Fragment(), Contract.ViewPage {



    private var mPage: Int = 0
    @Inject
    lateinit var presenter: Presenter
    lateinit var adapter: DiscoverAdapter
    var localadapter = DiscoverAdapter(this)
    var gridLayoutManager =  GridLayoutManager(context,2)
    var isLoading = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

        }

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val totalItemCount = gridLayoutManager.getItemCount()
            val firstPosition = gridLayoutManager.findFirstVisibleItemPosition();
            val visibleitemcount = gridLayoutManager.getChildCount()
             //вычисляем момент когда пора загружать следующую порцию покемонов
            if (firstPosition + visibleitemcount >= totalItemCount){
                if (!isLoading){
                    presenter.setPosition(firstPosition,visibleitemcount)

                }

            }

        }
    }

    companion object {
        val ARG_PAGE = "ARG_PAGE"

        fun newInstance(page: Int): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE, page);
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getArguments() != null) {
            mPage = getArguments()!!.getInt(ARG_PAGE);
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.instance.initPageComponent(this).inject(this)


        return inflater!!.inflate(R.layout.fragment_page, container, false)
    }

    override fun updatelocalpokemons(pokemons: List<Pokemons>) {
        localadapter.changedata(pokemons)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mPage == 1){
            rvDiscover.layoutManager =  gridLayoutManager
            rvDiscover.setHasFixedSize(true)
            adapter = DiscoverAdapter(this)
            rvDiscover.adapter=adapter
            rvDiscover.addOnScrollListener(scrollListener)
            presenter.getPokemons()

        } else if (mPage == 2){
            rvPokedex.layoutManager = gridLayoutManager
            rvPokedex.visibility = View.VISIBLE
            rvPokedex.setHasFixedSize(true)
            rvPokedex.adapter = localadapter
            presenter.getLocalPokemons()
        }


    }

    override fun onResume() {
        super.onResume()
        presenter.getLocalPokemons()
    }

    override fun onPokemonClick(pokemon: Pokemons) {
        val intent = Intent(context,PokemonActivity::class.java)
        intent.putExtra("id", pokemon.id)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        App.instance.destroyPageComponent()
        presenter.unsubscribe()
    }

    override fun showprogress() {
        isLoading = true
        progressBar.visibility = View.VISIBLE
    }

    override fun dismissprogress() {
        isLoading = false
        progressBar.visibility = View.INVISIBLE
    }

    override fun showerror(error: String) {
        textError.visibility = View.INVISIBLE
        val mes = "Ошибка ${error}"
        textError.text = mes
    }

    override fun updatelistpokemons(pokemons: List<Pokemons>) {
        adapter.changedata(pokemons)
    }
}
