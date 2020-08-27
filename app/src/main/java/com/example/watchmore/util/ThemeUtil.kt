package com.example.watchmore.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes
import com.example.watchmore.R


object ThemeUtil {
    fun setBaseTheme(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            "MyThemeShared", Context.MODE_PRIVATE
        )
        val themeType = sharedPreferences.getInt("theme_type", 1)
        val themeId: Int
        themeId = when (themeType) {
            ThemeColors.THEME_GREEN -> R.style.AppThemeNoActionBar_Green
            ThemeColors.ThEME_BLUE -> R.style.AppThemeNoActionBar_Blue
            ThemeColors.THEME_GREY -> R.style.AppThemeNoActionBar_Grey
            ThemeColors.THEME_YELLOW -> R.style.AppThemeNoActionBar_Yellow
            else -> R.style.AppThemeNoActionBar
        }
        context.setTheme(themeId)
    }

    fun setNewTheme(context: Context, theme: Int): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            "MyThemeShared", Context.MODE_PRIVATE
        )
        val themeType = sharedPreferences.getInt("theme_type", 1)
        if(themeType == theme)
            return false
        val e = sharedPreferences.edit()
        e.putInt("theme_type", theme)
        //        e.apply();
        return e.commit() //有返回值
    }

    fun getColorFromTheme(theme: Resources.Theme, @AttrRes id: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(id, typedValue, true)
        return typedValue.data
    }

    object ThemeColors {
        const val THEME_GREEN = 1
        const val ThEME_BLUE = 2
        const val THEME_YELLOW = 3
        const val THEME_GREY = 4
    }
}