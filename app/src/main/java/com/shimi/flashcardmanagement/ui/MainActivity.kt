package com.shimi.flashcardmanagement.ui

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.shimi.flashcardmanagement.R
import com.shimi.flashcardmanagement.databinding.ActivityMainBinding
import com.shimi.flashcardmanagement.local.HardCoddedLanguages
import com.shimi.flashcardmanagement.local.Repository
import com.shimi.flashcardmanagement.model.Language
import com.shimi.flashcardmanagement.modelBinding.CardsViewModel
import com.shimi.flashcardmanagement.modelBinding.CardsViewModelFactory
import com.vectormax.streaming.helpers.PrefsHelper


class MainActivity : AppCompatActivity() {

    private val mRepository = Repository()
    private lateinit var mViewDataBinding: ActivityMainBinding
    private val mViewModel: CardsViewModel by viewModels { CardsViewModelFactory(mRepository) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mRepository.clearDB()
        initUi()
    }

    private fun initUi() {
        setDataBinding()
        setSupportActionBar(findViewById(R.id.toolbar))
        setupListAdapter()
        mViewDataBinding.fab.setOnClickListener { view ->
            addCardDialog()
        }
    }

    private fun setDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.viewmodel = mViewModel
    }

    private fun setupListAdapter() {
        mViewDataBinding.flashRecycler.adapter = FlashCardAdapter(mViewModel)
        mViewDataBinding.flashRecycler.layoutAnimation = loadLayoutAnimation()
    }

    private fun loadLayoutAnimation():LayoutAnimationController{
        return AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
    }

    private fun addCardDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.add_card_title))

        val question = EditText(this)
        question.setPadding(20)
        question.hint = getString(R.string.add_card_question_hint) //optional

        val answer = EditText(this)
        answer.setPadding(20)
        answer.hint = getString(R.string.add_card_answer_hint) //optional

        val lay = LinearLayout(this)
        lay.setPadding(40)
        lay.orientation = LinearLayout.VERTICAL
        lay.addView(question)
        lay.addView(answer)
        builder.setView(lay)

        // Set up the buttons
        builder.setPositiveButton("Add") { dialog, whichButton -> //get the two inputs
            mViewModel.addCardsToDB(question.text.toString(), answer.text.toString())
        }
        builder.setNegativeButton("Cancel") { dialog, whichButton -> dialog.cancel() }
        builder.show()
    }

    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        menu.getItem(2).title = getString(R.string.action_set_language)+
                ": "+PrefsHelper.getSelectedLanguage().toUpperCase()
        return super.onMenuOpened(featureId, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
            R.id.action_add_language -> {
                val menu = createMenu(HardCoddedLanguages.getHardCoddedLanguages(), mViewDataBinding.toolbar)
                menu.setOnMenuItemClickListener {
                    val lang = (it.actionView.tag as Language)
                    Snackbar.make(mViewDataBinding.root, "Language Added : {${lang.name}}", Snackbar.LENGTH_LONG)
                            .show()
                    mViewModel.addSupportedLanguageToDB(lang)
                    true
                }
                menu.show()
            }
            R.id.action_set_language -> {
                val supportedLanguages = mRepository.getSupportedLanguages()
                supportedLanguages.add(0, Language(0, "English", "en"))
                val menu = createMenu(supportedLanguages, mViewDataBinding.toolbar)
                menu.setOnMenuItemClickListener {
                    PrefsHelper.setLastSelectedLanguage((it.actionView.tag as Language).code)
                    mViewModel.loadCards()
                    true
                }
                menu.show()
            }
            R.id.action_delete_language -> {
                val supportedLanguages = mRepository.getSupportedLanguages()
                val menu = createMenu(supportedLanguages, mViewDataBinding.toolbar)
                menu.setOnMenuItemClickListener {
                    mViewModel.deleteSupportedLanguageFromDB((it.actionView.tag as Language))
                    true
                }
                menu.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createMenu(supportedLanguages: List<Language>, view: View): PopupMenu {
        val menu = PopupMenu(this, view)
        menu.gravity = Gravity.END
        supportedLanguages.forEach {
            val v = View(this)
            v.tag = it
            menu.menu.add(it.name).actionView = v
        }
        return menu
    }
}