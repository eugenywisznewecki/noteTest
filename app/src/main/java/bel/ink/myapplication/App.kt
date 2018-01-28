package bel.ink.myapplication

import android.app.Application
import bel.ink.myapplication.Dagger.Component.AppComponent
import bel.ink.myapplication.Dagger.Component.DaggerAppComponent
import bel.ink.myapplication.Dagger.Modules.AppModule
import bel.ink.myapplication.DataBaseAPI.DataBaseEntity
import bel.ink.myapplication.Models.Entity.Note
import com.reactiveandroid.ReActiveAndroid
import com.reactiveandroid.ReActiveConfig
import com.reactiveandroid.internal.database.DatabaseConfig


/**
 * Created by User on 27.01.2018.
 */
class App : Application() {

	companion object {
		lateinit var component: AppComponent
	}

	override fun onCreate() {
		super.onCreate()

		//Dagger
		component = builComponent()

		//DB
		val appDatabaseConfig = DatabaseConfig.Builder(DataBaseEntity::class.java)
				.addModelClasses(Note::class.java)
				.build()

		ReActiveAndroid.init(ReActiveConfig.Builder(this)
				.addDatabaseConfigs(appDatabaseConfig)
				.build())
	}

	private fun builComponent(): AppComponent {
		return DaggerAppComponent.builder()
				.appModule(AppModule(this))
				.build()
	}

}

