package br.svcdev.githubclient

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.svcdev.githubclient.databinding.ActivityMainBinding
import br.svcdev.githubclient.presenter.Presenter
import br.svcdev.githubclient.view.MainView

class MainActivity : AppCompatActivity(), MainView, View.OnClickListener {
    private val presenter = Presenter(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    private fun init() {
        binding.btnCounter1.setOnClickListener(this)
        binding.btnCounter2.setOnClickListener(this)
        binding.btnCounter3.setOnClickListener(this)
    }

    override fun setButtonOneText(text: String?) {
        binding.btnCounter1.text = text
    }

    override fun setButtonTwoText(text: String?) {
        binding.btnCounter2.text = text
    }

    override fun setButtonThreeText(text: String?) {
        binding.btnCounter3.text = text
    }

    override fun onClick(view: View) {
        when(view){
            binding.btnCounter1 -> presenter.onButtonOneClick()
            binding.btnCounter2 -> presenter.onButtonTwoClick()
            binding.btnCounter3 -> presenter.onButtonThreeClick()
        }
    }
}