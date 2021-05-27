package com.bagasbest.fundamental2.room.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.databinding.ActivityMyRoomNoteAddUpdateBinding
import com.bagasbest.fundamental2.room.database.Note
import com.bagasbest.fundamental2.room.helper.DateHelper
import com.bagasbest.fundamental2.room.ui.insert.viewmodel.NoteAddUpdateViewModel
import com.bagasbest.fundamental2.room.ui.insert.viewmodel.ViewModelFactory

class MyRoomNoteAddUpdateActivity : AppCompatActivity() {

    private var binding: ActivityMyRoomNoteAddUpdateBinding? = null
    private lateinit var viewModel: NoteAddUpdateViewModel

    companion object {
        const val EXTRA_NOTE = "extranote"
        const val EXTRA_POSITION = "extrapos"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val REQUEST_DELETE = 300
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var note: Note? = null
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRoomNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = obtainViewModel(this)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            note = Note()
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (note != null) {
                note?.let {
                    binding?.etTitle?.setText(it.title)
                    binding?.edtDescription?.setText(it.description)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.btnSubmit?.text = btnTitle

        binding?.btnSubmit?.setOnClickListener {
            saveOrUpdateNote()
        }
    }

    private fun saveOrUpdateNote() {
        val title = binding?.etTitle?.text.toString().trim()
        val description = binding?.edtDescription?.text.toString().trim()

        if (title.isEmpty()) {
            binding?.etTitle?.error = getString(R.string.empty)
        } else if (description.isEmpty()) {
            binding?.edtDescription?.error = getString(R.string.empty)
        } else {
            note.let { note ->
                note?.title = title
                note?.description = description
            }

            val intent = Intent().apply {
                putExtra(EXTRA_NOTE, note)
                putExtra(EXTRA_POSITION, position)
            }

            if (isEdit) {
                viewModel.update(note as Note)
                setResult(RESULT_UPDATE, intent)
                finish()
            } else {
                note.let {
                    it?.date = DateHelper.getCurrentDate()
                }
                viewModel.insert(note as Note)
                setResult(RESULT_ADD, intent)
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun obtainViewModel(activity: MyRoomNoteAddUpdateActivity): NoteAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(NoteAddUpdateViewModel::class.java)
    }

    override fun onSupportActionModeFinished(mode: ActionMode) {
        onBackPressed()
        super.onSupportActionModeFinished(mode)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if(isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogTitle = getString(R.string.delete)
            dialogMessage = getString(R.string.message_delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) {_, _ ->
                if(!isDialogClose) {
                    viewModel.delete(note as Note)

                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    setResult(RESULT_DELETE, intent)
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) {dialog,_ ->
                dialog.dismiss()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}