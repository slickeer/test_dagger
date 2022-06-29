package com.example.swipemenu

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStore

fun Fragment.logInfo(fragInfo: String) {
    val test = host
    val activity = activity
    val frag = parentFragment

    Log.d("CHILD $fragInfo FRAG", "HOST -> $test || Activity -> $activity || Fragment -> $frag")

}

/**
 * use this method in lifecycle methods like. onCreateView, onViewCreated
 * cause fragment can be created without attaching to activity or parent fragment
 * but those method will run when we attach it, then we can find where it's attached by using this method.
 *
 *
 * if fragment is attached to parentFragment it will have parentFragment and activity set ([requireParentFragment] [requireActivity])
 * but if it's attached to activity it will have parentFragment as null ([requireParentFragment]) and activity ([requireActivity]) set.
 */
fun Fragment.getParent(): ViewModelStore {
    if (parentFragment != null)
        return requireParentFragment().viewModelStore

    //Default, cause fragment needs to be attached to activity
    return requireActivity().viewModelStore
}