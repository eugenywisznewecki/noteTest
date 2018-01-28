package bel.ink.myapplication.DataBaseAPI

import android.content.Context
import bel.ink.myapplication.Models.Entity.Note
import com.reactiveandroid.query.Select


/**
 * Created by User on 27.01.2018.
 * TODO USUAL CRUD! without sugar !!! no time!
 */
class DataBaseAPI(context: Context) {

	fun saveNoteInDB(note: Note): Long = note.save()

	fun setWholeNotes(): MutableList<Note> = Select.from(Note::class.java).fetch()

	fun getNoteByIdFrmDB(noteId: Long): Note? = Select.from(Note::class.java).where("id = ?", noteId).fetchSingle()

	fun deleteNoteFromDB(note: Note) = note.delete()

}
