package com.aperfectpolygon.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

class TextInputLayout : TextInputLayout {
	constructor(context: Context) : super(context) {
		initialize(null)
	}
	
	constructor(
		context: Context,
		attrs: AttributeSet?,
	) : super(context, attrs) {
		initialize(attrs)
	}
	
	constructor(
		context: Context,
		attrs: AttributeSet?,
		defStyleAttr: Int,
	) : super(context, attrs, defStyleAttr) {
		initialize(attrs)
	}
	
	private fun initialize(attrs: AttributeSet?) {
		var font = createNormal
		if (attrs != null) {
			context.obtainStyledAttributes(attrs, R.styleable.TextInputLayout).apply {
				if (hasValue(R.styleable.TextInputLayout_fontStyle)) {
					when (FontSTYLE.values()[getInt(R.styleable.TextInputLayout_fontStyle, 0)]) {
						FontSTYLE.NORMAL -> createNormal
						FontSTYLE.BOLD -> createBold
						FontSTYLE.LIGHT -> createLight
						FontSTYLE.MEDIUM -> createMedium
					}.also { font = it }
				}
			}.recycle()
		}
		typeface = font
		
	}
	
	fun configError(@StringRes errorMessage: Int?) = apply {
		isErrorEnabled = errorMessage != null
		error = errorMessage?.let { context.getString(it) }
	}
	
	fun configError(errorMessage: String) = apply {
		error = errorMessage
	}
}