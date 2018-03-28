package ru.helen.pokemon.pagefragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.helen.pokemon.App
import ru.helen.pokemon.R
import ru.helen.pokemon.model.Pokemon


class PageFragment : Fragment(), Contract.ViewPage {


    private var mPage: Int = 0

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
}// Required empty public constructor
