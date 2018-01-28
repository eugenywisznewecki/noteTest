package bel.ink.myapplication.Presenters

import android.util.Log
import bel.ink.myapplication.App
import bel.ink.myapplication.Subcribes.DeleteNoteActionWarn
import bel.ink.myapplication.Subcribes.EditNotesActionWarn
import bel.ink.myapplication.DataBaseAPI.DataBaseAPI
import bel.ink.myapplication.Models.Entity.Note
import bel.ink.myapplication.View.MainActView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * Created by User on 27.01.2018.
 */
@InjectViewState
class Presenter : MvpPresenter<MainActView>() {


	@Inject
	lateinit var dataBaseAPI: DataBaseAPI

	private lateinit var notesList: MutableList<Note>

	init {
		App.component.inject(this)
		EventBus.getDefault().register(this)
	}


/*	fun deleteAllNotes() {
		dataBaseAPI.deleteAllNotes()
		notesList!!.removeAll(notesList)
		viewState.onAllNotesDeleted()
	}*/


	fun deleteNoteByPosition(position: Int) {
		val note = notesList[position]
		dataBaseAPI.deleteNoteFromDB(note)
		notesList.remove(note)
		viewState.onNoteDeleted()
	}

	fun openNewNote() {
		val note = Note("Введите название", "введите текст")

		//TODO is it nessesary?

		if (notesList == null) {
			notesList = mutableListOf(note)
		} else {
			notesList.add(note)
		}
		dataBaseAPI.saveNoteInDB(note)
		viewState.openNoteEditScreen(note.id)


	}

	fun openNote(position: Int) {
		viewState.openNoteEditScreen(notesList[position].id)
	}


	@Subscribe
	fun onNoteEdit(action: EditNotesActionWarn) {
		val notePosition = getNotePositionById(action.noteId)
		notesList[notePosition] = dataBaseAPI.getNoteByIdFrmDB(action.noteId)!!

	}

	@Subscribe
	fun onNoteDelete(action: DeleteNoteActionWarn) {
		Log.d("Notelin", "onDeleted" + action.noteId)
		val notePosition = getNotePositionById(action.noteId)
		notesList.removeAt(notePosition)
		viewState.updateView()
	}


	fun showNoteDeleteDialog(position: Int) {
		viewState.showNoteDeleteDialog(position)
	}

	fun hideNoteDeleteDialog() {
		viewState.hideNoteDeleteDialog()
	}

	override fun onFirstViewAttach() {
		super.onFirstViewAttach()
		loadAllNotes()
	}

	fun loadAllNotes() {
		notesList = dataBaseAPI.setWholeNotes()
		viewState.onNotesListLoaded(notesList)

		//Log.d("TAG", notesList.toString() +" presenter all notes")
	}

	private fun getNotePositionById(noteId: Long) = notesList?.indexOfFirst { it.id == noteId }


}