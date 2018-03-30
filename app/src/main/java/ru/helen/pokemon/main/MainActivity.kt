package ru.helen.pokemon.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.helen.pokemon.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager.adapter = PageAdapter(getSupportFragmentManager(), this)
        tablayout.setupWithViewPager(viewpager)
    }
}
