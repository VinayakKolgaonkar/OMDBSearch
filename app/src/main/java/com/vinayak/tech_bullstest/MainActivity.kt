package com.vinayak.tech_bullstest

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity()
{
    lateinit var context: Context
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var rv_movies: RecyclerView
    lateinit var etSearch: EditText
    lateinit var txtToolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        initView()

        etSearch.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
            {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
            {
                if (s.toString().length > 0)
                {
                    getMovies(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun onStart() {
        super.onStart()
        getMovies("Happy")
    }

    private fun initView()
    {
        etSearch = findViewById(R.id.etSearch)
        rv_movies = findViewById(R.id.rv_movies)
        layoutManager = LinearLayoutManager(context)
        rv_movies.layoutManager = layoutManager
        txtToolbarTitle = findViewById(R.id.txtToolbarTitle)

        txtToolbarTitle.text = "Tech-Bulls"
    }

    private fun getMovies(serachString: String)
    {
        MovieApi().getMovies(serachString,"ed0c805").enqueue(object : Callback<MoviesMainModel>
        {
            override fun onResponse(call: Call<MoviesMainModel>, response: Response<MoviesMainModel>) {

                if (response.isSuccessful)
                {
                    println("Response Successful")

                    if (response.body()!!.Search != null)
                    {
                        moviesAdapter = MoviesAdapter(context,response.body()!!.Search!!)
                        rv_movies.adapter = moviesAdapter
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<MoviesMainModel>, t: Throwable) {
                println("Response failed")
            }
        })
    }
}