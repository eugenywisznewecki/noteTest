package bel.ink.myapplication.Ui.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import bel.ink.myapplication.Models.Entity.Note
import bel.ink.myapplication.Presenters.DetailPresenter
import bel.ink.myapplication.R
import bel.ink.myapplication.View.DetailActView
import com.afollestad.materialdialogs.MaterialDialog
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailActivity : MvpAppCompatActivity(), DetailActView {


	companion object {
		const val ID_FOR_INTENT = "_id"

		fun getIntentFrmDelail(context: Context, noteId: Long): Intent {
			val intent = Intent(context, DetailActivity::class.java)
			intent.putExtra(ID_FOR_INTENT, noteId)
			return intent
		}
	}

	@InjectPresenter
	lateinit var detailPresenter: DetailPresenter

	private var noteDeleteDialog: MaterialDialog? = null


	@ProvidePresenter
	fun provideHelloPresenter(): DetailPresenter {
		val noteId = intent.extras.getLong(ID_FOR_INTENT, -1)
		Log.d("TAG", " Was get " + noteId.toString())
		return DetailPresenter(noteId)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)

		/*val noteId = intent.extras.getLong("id", -1)
		DetailPresenter(noteId)*/

		textViewTitle.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
			if (hasFocus) {
				val editText = view as EditText
				editText.setSelection((editText.text.length))
			}
		}
	}

	override fun showNote(note: Note) {
		textViewTitle.setText(note.title)
		textViewText.setText(note.text)
	}

	override fun showNoteMaterialDialogDelete() {
		noteDeleteDialog = MaterialDialog.Builder(this)
				.title(getString(R.string.note_delete))
				.content(getString(R.string.note_deletion_message))
				.positiveText(getString(R.string.yes))
				.negativeText(getString(R.string.no))
				.onPositive { _, _ ->
					detailPresenter.hideNoteDeleteDialog()
					detailPresenter.deleteNote()
				}
				.onNegative { _, _ -> detailPresenter.hideNoteDeleteDialog() }
				.cancelListener { detailPresenter.hideNoteDeleteDialog() }
				.show()
	}

	override fun hideNoteMaterialDialogDelete() {
		noteDeleteDialog?.dismiss()
	}

	override fun onNoteSaved() {
		toast("Saved, you can press BACK")
	}

	override fun onNoteDeleted() {
		toast("deleted")
		finish()
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.note, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.meneViewSave -> detailPresenter.saveNote(textViewTitle.text.toString(), textViewText.text.toString())
		//TODO + jump to mainActivity then here or nearby

			R.id.menuViewDelete -> detailPresenter.showNoteDeleteDialog()

		}
		return super.onOptionsItemSelected(item)
	}

}
