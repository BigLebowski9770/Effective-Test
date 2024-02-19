package com.loves2spooge.effectivetest.di

import com.loves2spooge.effectivetest.presetnation.activity.MainActivity
import dagger.Component

@Component()
interface AppComponent {

    fun inject(activity: MainActivity)
}