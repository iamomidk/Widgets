package com.aperfectpolygon.widgets

import android.graphics.Typeface
import android.view.View

enum class FontSTYLE { NORMAL, BOLD, LIGHT, MEDIUM }

val View.createNormal: Typeface
	get() = Typeface.createFromAsset(context.assets, "fonts/iran_sans_x_regular.ttf")

val View.createBold: Typeface
	get() = Typeface.createFromAsset(context.assets, "fonts/iran_sans_x_bold.ttf")

val View.createLight: Typeface
	get() = Typeface.createFromAsset(context.assets, "fonts/iran_sans_x_light.ttf")

val View.createMedium: Typeface
	get() = Typeface.createFromAsset(context.assets, "fonts/iran_sans_x_medium.ttf")
