package bel.ink.myapplication.Models.Entity

import bel.ink.myapplication.DataBaseAPI.DataBaseEntity
import com.reactiveandroid.Model
import com.reactiveandroid.annotation.Column
import com.reactiveandroid.annotation.PrimaryKey
import com.reactiveandroid.annotation.Table

/**
 * Created by User on 27.01.2018.
 */

@Table(name = "Notes", database = DataBaseEntity::class)
class Note : Model {

	@PrimaryKey
	var id: Long = 0

	@Column(name = "Title")
	var title: String? = null

	@Column(name = "Text")
	var text: String? = null


	constructor(title: String, text: String) {

		this.title = title
		this.text = text
	}

	constructor()
}