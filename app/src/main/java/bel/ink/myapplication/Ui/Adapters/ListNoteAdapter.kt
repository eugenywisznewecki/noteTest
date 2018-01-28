package bel.ink.myapplication.Ui.Adapters

/**
 * Created by User on 28.01.2018.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import bel.ink.myapplication.Models.Entity.Note
import bel.ink.myapplication.R
import bel.ink.myapplication.Ui.Activity.DetailActivity


class ListNoteAdapter(private val notesList: List<Note>) : RecyclerView.Adapter<ListNoteAdapter.ViewHolder>() {


	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListNoteAdapter.ViewHolder {
		val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item, viewGroup, false)
		view.setOnClickListener {
			Log.d("TAG", "PUSHED ")
		}
		return ListNoteAdapter.ViewHolder(view)
	}

	override
	fun onBindViewHolder(viewHolder: ListNoteAdapter.ViewHolder, position: Int) {

		val note = notesList[position]
		viewHolder.noteTitle.text = note.title
		viewHolder.noteText.text = note.text
		//viewHolder.position = position as Long

	}

	override fun getItemCount(): Int {
		return notesList.size
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

		var contextIN: Context? = null

		var noteTitle: TextView = itemView.findViewById(R.id.textViewNoteTitle) as TextView
		var noteText: TextView = itemView.findViewById(R.id.textViewNoteText) as TextView

		init {
			itemView.setOnClickListener(this)
			contextIN = itemView.context
		}

		//TODO: it's not MVP already!
		override fun onClick(p0: View?) {

			val intent = DetailActivity.getIntentFrmDelail(contextIN!!, getPosition().toLong() + 1)
			//+ because of ID sql !!! dont forget it, stupid idiot!
			Log.d("TAG", "noteID" + position.toLong().toString())
			contextIN?.startActivity(intent)
		}
	}
}

