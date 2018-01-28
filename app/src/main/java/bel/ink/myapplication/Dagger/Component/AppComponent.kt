package bel.ink.myapplication.Dagger.Component

import bel.ink.myapplication.Dagger.Modules.AppModule
import bel.ink.myapplication.Presenters.DetailPresenter
import bel.ink.myapplication.Presenters.Presenter
import bel.ink.myapplication.Ui.Activity.DetailActivity
import bel.ink.myapplication.Ui.Activity.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by User on 27.01.2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {


	fun inject(presenter: Presenter)

	fun inject(detailPresenter: DetailPresenter)

	fun inject(activity: MainActivity)

	fun inject(detaiilActivity: DetailActivity)

	/*
	fun inject(intentService: WeatherIService)
*/

}