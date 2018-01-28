package bel.ink.myapplication.Presenters

import android.util.Log
import bel.ink.myapplication.App
import bel.ink.myapplication.DataBaseAPI.DataBaseAPI
import bel.ink.myapplication.Models.Entity.Note
import bel.ink.myapplication.Subcribes.DeleteNoteActionWarn
import bel.ink.myapplication.Subcribes.EditNotesActionWarn
import bel.ink.myapplication.View.DetailActView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by User on 28.01.2018.
 */
@InjectViewState
class DetailPresenter(val noteId: Long) : MvpPresenter<DetailActView>() {

	//public var noteIdDuplicatre: Int = 0;

	@Inject
	lateinit var dataBaseAPI: DataBaseAPI

	private lateinit var note: Note

	init {
		App.component.inject(this)
	}

	override fun onFirstViewAttach() {
		super.onFirstViewAttach()

		if (noteId < 0 || noteId == null) {
			Log.d("TAG", "noteID == null")
		} else if (noteId == 0L) {
			Log.d("TAG", "noteID == 0")
		} else {
			note = dataBaseAPI.getNoteByIdFrmDB(noteId)!!
			viewState.showNote(note)
			Log.d("TAG", "noteID == 0")
		}
	}

	fun saveNote(title: String, text: String) {
		note.text = text
		note.title = title
		dataBaseAPI.saveNoteInDB(note)
		EventBus.getDefault().post(EditNotesActionWarn(note.id))
		viewState.onNoteSaved()
	}

	fun deleteNote() {
		val noteId = note.id
		dataBaseAPI.deleteNoteFromDB(note)
		EventBus.getDefault().post(DeleteNoteActionWarn(noteId))
		viewState.onNoteDeleted()
	}

	fun showNoteDeleteDialog() {
		viewState.showNoteMaterialDialogDelete()
	}

	fun hideNoteDeleteDialog() {
		viewState.hideNoteMaterialDialogDelete()
	}


}
