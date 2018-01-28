package bel.ink.myapplication.View

import bel.ink.myapplication.Models.Entity.Note
import com.arellomobile.mvp.MvpView

/**
 * Created by User on 28.01.2018.
 */
interface DetailActView : MvpView {

	fun showNote(note: Note)

	fun onNoteSaved()

	fun onNoteDeleted()

	fun showNoteMaterialDialogDelete()

	fun hideNoteMaterialDialogDelete()

}
