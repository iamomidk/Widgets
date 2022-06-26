package com.aperfectpolygon.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class EditText : TextInputEditText {
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
			context.obtainStyledAttributes(attrs, R.styleable.EditText).apply {
				if (hasValue(R.styleable.EditText_fontStyle)) {
					when (FontSTYLE.values()[getInt(R.styleable.EditText_fontStyle, 0)]) {
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
	
	
	val textString: String
		get() = if (text == null) "" else text.toString()
	val isEmpty: Boolean
		get() = textString.isEmpty()
	val isNotEmpty: Boolean
		get() = textString.isNotEmpty()
	val makeEmpty: Unit
		get() = setText("")
}