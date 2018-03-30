package ru.helen.pokemon.pagefragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_page.*
import ru.helen.pokemon.App
import ru.helen.pokemon.R
import ru.helen.pokemon.model.Pokemon
import javax.inject.Inject


class PageFragment : Fragment(), Contract.ViewPage {

    private var mPage: Int = 0
    @Inject
    lateinit var presenter: Presenter
    lateinit var adapter: DiscoverAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvDiscover.layoutManager = GridLayoutManager(activity,2)
        rvDiscover.setHasFixedSize(true)
        adapter = DiscoverAdapter(this)
        rvDiscover.adapter=adapter
        presenter.getPokemons(6,0)
       // test!!.text = "Fragment #$mPage"
    }

    override fun onPokemonClick(pokemon: Pokemon) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        super.onStop()
        App.instance.destroyPageComponent()
        //TODO отписать rx
    }

    override fun showprogress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissprogress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showerror(error: String) {
        Log.e("ERROR", error)
    }

    override fun updatelistpokemons(pokemons: List<Pokemon>) {
        adapter.changedata(pokemons)
    }
}
