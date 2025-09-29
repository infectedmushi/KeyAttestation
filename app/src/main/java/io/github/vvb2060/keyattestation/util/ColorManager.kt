package io.github.vvb2060.keyattestation.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import io.github.vvb2060.keyattestation.R

object ColorManager {

    private const val PREFS_NAME = "color_prefs"
    private const val KEY_THEME = "theme_id"

    fun showColorPickerDialog(activity: Activity) {
        val colors = arrayOf(
            activity.getString(R.string.color_default_gray),
		    activity.getString(R.string.color_original),
			activity.getString(R.string.color_pure_black),
			activity.getString(R.string.color_blue),
            activity.getString(R.string.color_green),
			activity.getString(R.string.color_orange),
            activity.getString(R.string.color_pink),              			
		    activity.getString(R.string.color_purple),
            activity.getString(R.string.color_red)
        )
        val themeResIds = intArrayOf(
            R.style.Theme_Default_Gray,
		    R.style.Theme_Original,
			R.style.Theme_Pure_Black,
			R.style.Theme_Blue,
            R.style.Theme_Green,
			R.style.Theme_Orange,
			R.style.Theme_Pink,
		    R.style.Theme_Purple,
            R.style.Theme_Red
        )

        val prefs = getSharedPreferences(activity)
        val savedThemeId = prefs.getInt(KEY_THEME, R.style.Theme_Default_Gray)
        val savedColorIndex = themeResIds.indexOf(savedThemeId)

        AlertDialog.Builder(activity)
            .setTitle(R.string.menu_color)
            .setSingleChoiceItems(colors, savedColorIndex) { dialog, which ->
                saveTheme(activity, themeResIds[which])
                dialog.dismiss()
                activity.recreate()
            }
            .show()
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun saveTheme(context: Context, @StyleRes themeId: Int) {
        getSharedPreferences(context).edit()
            .putInt(KEY_THEME, themeId)
            .apply()
    }

    @StyleRes
    fun getThemeResId(context: Context): Int {
        val prefs = getSharedPreferences(context)
        return prefs.getInt(KEY_THEME, R.style.Theme_Default_Gray)
    }
}