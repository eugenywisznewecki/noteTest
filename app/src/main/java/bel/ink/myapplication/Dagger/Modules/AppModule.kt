package bel.ink.myapplication.Dagger.Modules

import android.app.Application
import android.content.Context
import bel.ink.myapplication.DataBaseAPI.DataBaseAPI
import bel.ink.myapplication.Presenters.Presenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ink on 27.01.2018.
 */
@Module
class AppModule {
	constructor(app: Application) {
		this.context = app
	}

	private val context: Context

	@Provides
	fun providesContext(): Context = context

	@Provides
	@Singleton
	fun provideDataBaseAPI(context: Context): DataBaseAPI =
			DataBaseAPI(context)

	@Provides
	@Singleton
	fun providePresenter(): Presenter =
			Presenter()

/*
	@Provides
	@Singleton
	fun provideCommunicator(context: Context): Communicator = Communicator(context)

	@Provides
	@Singleton
	fun provideWifiChecker(context: Context): WifiChecker = WifiChecker(context)

	@Provides
	@Singleton
	fun provideBadWeatherGuard(context: Context): BadWeatherGuard = BadWeatherGuard(context)*/

}