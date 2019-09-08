package ru.skillbranch.devintensive.ui.archive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_archive.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.setBackgroundColor
import ru.skillbranch.devintensive.extensions.setTextColor
import ru.skillbranch.devintensive.ui.adapters.ChatAdapter
import ru.skillbranch.devintensive.ui.adapters.ChatItemTouchHelperCallback
import ru.skillbranch.devintensive.utils.Utils
import ru.skillbranch.devintensive.viewmodels.ArchiveViewModel

class ArchiveActivity : AppCompatActivity() {
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var viewModel: ArchiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)
        initToolbar()
        initViews()
        initViewModel()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun initViews() {
        title = "Архив чатов"
        chatAdapter = ChatAdapter {
            Snackbar.make(rv_archive_list, "Click on ${it.title}", Snackbar.LENGTH_SHORT)
                .setTextColor(Utils.getColorForNightUI(this, R.attr.colorSnackbarText))
                .setBackgroundColor(Utils.getColorForNightUI(this, R.attr.colorSnackbar))
                .show()
        }
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val touchCallback = ChatItemTouchHelperCallback(chatAdapter) {
            val id = it.id
            viewModel.restoreFromArchive(it.id)
            Snackbar.make(rv_archive_list, "Восстановить чат ${it.title} из архива?", Snackbar.LENGTH_LONG)
                .setTextColor(Utils.getColorForNightUI(this, R.attr.colorSnackbarText))
                .setBackgroundColor(Utils.getColorForNightUI(this, R.attr.colorSnackbar))
                .setAction("ОТМЕНА") { viewModel.addToArchive(id) }
                .show()
        }
        val touchHelper = ItemTouchHelper(touchCallback)
        touchHelper.attachToRecyclerView(rv_archive_list)

        with(rv_archive_list) {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(this@ArchiveActivity)
            addItemDecoration(divider)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ArchiveViewModel::class.java)
        viewModel.getChatData().observe(this, Observer { chatAdapter.updateData(it) })
    }
}