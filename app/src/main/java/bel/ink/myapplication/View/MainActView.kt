package bel.ink.myapplication.View

import bel.ink.myapplication.Models.Entity.Note
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by User on 28.01.2018.
 */

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainActView : MvpView {

	fun onNotesListLoaded(notes: List<Note>)

	fun updateView()

	fun onNoteDeleted()

	fun showNoteDeleteDialog(notePosition: Int)

	fun hideNoteDeleteDialog()

	fun openNoteEditScreen(noteId: Long)

}