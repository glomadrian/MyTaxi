package com.github.glomadrian.mytaxi.corepresentation.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import javax.inject.Inject

typealias NavigatorCommand = (schema: String) -> Uri

class Navigator @Inject constructor(){

    companion object {
        private const val SCHEMA = "mytaxi"
    }

    fun navigate(context: Context, command: NavigatorCommand) {
        val intent =  Intent(Intent.ACTION_VIEW, command(SCHEMA))
        if (isValidIntent(intent, context)) {
            context.startActivity(intent)
        }
    }

    private fun isValidIntent( intent: Intent, context: Context) = intent.resolveActivity(context.packageManager) != null
}


