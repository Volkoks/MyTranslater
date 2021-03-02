package com.example.mytranslater.ui.screen_word

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mytranslater.R
import com.example.mytranslater.application.MyApp
import com.example.mytranslater.databinding.WordFragmentBinding
import com.example.mytranslater.model.state.AppState
import javax.inject.Inject

class WordFragment : Fragment(R.layout.word_fragment) {

    companion object {
        const val TAG_WORD = "com.example.mytranslater.ui.screen_word.tag_word"
        const val TAG_TRANSLATE = "com.example.mytranslater.ui.screen_word.tag_translate"
        const val TAG_URL = "com.example.mytranslater.ui.screen_word.tag_url"

        fun newInstance(word: String, translate: String, url: String) = WordFragment().apply {
            arguments = Bundle().apply {
                putString(TAG_WORD, word)
                putString(TAG_TRANSLATE, translate)
                putString(TAG_URL, url)
            }
        }
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: WordViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(WordViewModel::class.java)
    }

    private var binding: WordFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        binding = WordFragmentBinding.bind(view)
        val imageUrl = arguments?.getString(TAG_URL)
        val word = arguments?.getString(TAG_WORD)
        val translate = arguments?.getString(TAG_TRANSLATE)

        setDataToView(word, translate, imageUrl)

        viewModel.subscribe().observe(viewLifecycleOwner, {

        })

        binding?.swipeRefreshLayoutWordFragment?.setOnRefreshListener {
            stopRefreshingAnimate()
            setDataToView(word, translate, imageUrl)
        }
    }

    private fun setDataToView(word: String?, translate: String?, imageUrl: String?) {
        binding?.tvWfWord?.text = word ?: "Слово отсутствует"
        binding?.tvWfTranslate?.text = translate ?: "Перевод отсутствует"
        binding?.ivWfImage?.let {
            imageUrl?.let { it1 ->
                loadImage(it, it1)
            }
        }
    }

    private fun stopRefreshingAnimate() {
        if (binding?.swipeRefreshLayoutWordFragment?.isRefreshing == true) {
            binding?.swipeRefreshLayoutWordFragment?.isRefreshing = false
        }
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView)
            .load("https:$url")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopRefreshingAnimate()
                    binding?.ivWfImage?.setImageResource(R.drawable.warning)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopRefreshingAnimate()
                    return false
                }

            })
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.place_holder)
                    .centerCrop()
            )
            .into(imageView)
    }
}