package bel.ink.myapplication.Ui.Activity

import android.os.Bundle
import android.view.View
import bel.ink.myapplication.App
import bel.ink.myapplication.Models.Entity.Note
import bel.ink.myapplication.Presenters.Presenter
import bel.ink.myapplication.R
import bel.ink.myapplication.Ui.Adapters.ListNoteAdapter
import bel.ink.myapplication.View.MainActView
import com.afollestad.materialdialogs.MaterialDialog
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : MvpAppCompatActivity(), MainActView {

	@InjectPresenter
	lateinit var presenter: Presenter

	private var noteDeleteDialog: MaterialDialog? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		App.component.inject(this) // inject point


		plusAddViewButton.setOnClickListener {
			presenter.openNewNote()
		}
	}

	override fun onResume() {
		super.onResume()

		presenter.loadAllNotes()
	}

	override fun onNotesListLoaded(notes: List<Note>) {
		notesViewRecyclerList.adapter = ListNoteAdapter(notes)
		updateView()
	}

	override fun updateView() {
		//not right updated but now is ok)
		notesViewRecyclerList.adapter.notifyDataSetChanged()

		when {
			(notesViewRecyclerList.adapter.itemCount == 0) -> {
				notesViewRecyclerList.visibility = View.GONE
				textViewListEmpty.visibility = View.VISIBLE
			}
			(notesViewRecyclerList.adapter.itemCount > 0) -> {
				notesViewRecyclerList.visibility = View.VISIBLE
				textViewListEmpty.visibility = View.GONE
			}
		}
	}

	override fun onNoteDeleted() {
		updateView()
		toast("deleted")
	}

	override fun showNoteDeleteDialog(notePosition: Int) {
		noteDeleteDialog = MaterialDialog.Builder(this)
				.title(getString(R.string.note_delete))
				.content(getString(R.string.note_deletion_message))
				.positiveText(getString(R.string.yes))
				.negativeText(getString(R.string.no))
				.onPositive { _, _ ->
					presenter.deleteNoteByPosition(notePosition)

				}
				.onNegative { _, _ -> presenter.hideNoteDeleteDialog() }
				.cancelListener { presenter.hideNoteDeleteDialog() }
				.show()
	}

	override fun hideNoteDeleteDialog() {
		noteDeleteDialog?.dismiss()
	}

	override fun openNoteEditScreen(noteId: Long) {
		startActivity(DetailActivity.getIntentFrmDelail(this, noteId))
	}


}
