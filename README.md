# noteTest
 
/*
* MVP pattern is Used. Model - View - Presenter with Moxy,
* it annotates presenters, views, and models
* There are two presenters for each Activity, with injection them in activities by Moxy,
* Model - Note class with text and title string properties, and DataBase class
* implements work with SQL
* Views: DetailActView and MainActView implements methods to use in Views (Activities)
* All commands are given by Moxy ViewState instead of callbacks and so on.
* (But in RecyclerView List adapter MVP is slightly broken (set onclickListeners to Items with
* StartActivity)
*
* Dependency injections by Dagger2, injected only DbAPI to presenters.
* Two methods listen and subcribe for eventBus (events: Delete and Edit)
* */

//simply kotlin - all project with it
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"

    //for compatibility with old versions and implementation of MD and so on
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation "com.android.support:design:26.1.0"

    //use and inherit cardview styles and itself
    implementation "com.android.support:cardview-v7:26.1.0"

    //here unnessesary, because of no unit and tool tests in the project at all
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'

    // to simple implementation MVP pattern
    implementation "com.arello-mobile:moxy:1.5.3"
    implementation 'com.arello-mobile:moxy-app-compat:1.5.3'
    kapt 'com.arello-mobile:moxy-compiler:1.5.3'

    //ORM - some stupid problems with Kotlin 1.2
    //nice light ORM for android
    //compile 'com.github.satyan:sugar:1.5'

    //use this ORM first time, google helped
    implementation "com.reactiveandroid:reactiveandroid:1.2.1"

    //to use simple subscribes and work with events
    implementation "org.greenrobot:eventbus:3.0.0"

    //obvious part of every android project)) to make lists
    compile 'com.android.support:recyclerview-v7:26.1.0'

    // ready Material Dialogs, no necessity to implement manually -
    // used ready solutions
    implementation 'com.afollestad.material-dialogs:core:0.9.6.0'

    //also almost obvious part), Dagger2 - to provide dependency injections
    compile 'com.google.dagger:dagger:2.12'
    kapt 'com.google.dagger:dagger-compiler:2.12'

    // Anko Commons
    // used to simple toasts, activities transition,
    // if it were many threads, i would use anko asyncs. it much simplier
    compile "org.jetbrains.anko:anko:$anko_version"

    //to check and work with permissions
    /*compile 'com.karumi:dexter:4.1.1'*/
}
