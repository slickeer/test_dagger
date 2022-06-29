package com.example.swipemenu.di

import com.example.swipemenu.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun bindMain(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindParent(): ParentFrag

    @ContributesAndroidInjector
    abstract fun bindChild(): ChildFrag

    @ContributesAndroidInjector
    abstract fun bindSecondChild(): ChildSecondFrag

    @ContributesAndroidInjector
    abstract fun bindThirdChild(): ChildThirdFrag
}