@file:Suppress("PrivatePropertyName")

package com.aperfectpolygon.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getColor

class Button : AppCompatButton {
	
	fun setTextColor(
		selectedTextColor: Int = R.color.white,
		unSelectedTextColor: Int = R.color.titleTextColor,
	) {
		when {
			isSelected -> selectedTextColor
			else -> unSelectedTextColor
		}.also { setTextColor(getColor(context, it)) }
	}
	
	constructor(context: Context) : super(context) {
		initialize(null)
	}
	
	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
		initialize(attrs)
	}
	
	constructor(
		context: Context, attrs: AttributeSet?, defStyleAttr: Int,
	) : super(context, attrs, defStyleAttr) {
		initialize(attrs)
	}
	
	override fun isEnabled(): Boolean = try {
		super.isEnabled().also { isEnabled ->
			when {
				!isEnabled -> getColor(context, R.color.disableTextColor)
				else -> color
			}.also { setTextColor(it) }
		}
	} catch (e: Exception) {
		e.printStackTrace()
		false
	}
	
	override fun setEnabled(enabled: Boolean) {
		try {
			super.setEnabled(enabled)
			when {
				!enabled -> getColor(context, R.color.disableTextColor)
				else -> color
			}.also { setTextColor(it) }
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
	
	override fun setTextColor(color: Int) {
		this.color = color
		super.setTextColor(color)
	}
	
	var color = 0
	var autoColorSet = true
	
	@SuppressLint("ClickableViewAccessibility")
	private fun initialize(attrs: AttributeSet?) {
		var font = createNormal
		if (attrs != null) {
			context.obtainStyledAttributes(attrs, R.styleable.Button).apply {
				if (hasValue(R.styleable.Button_fontStyle)) {
					when (FontSTYLE.values()[getInt(R.styleable.Button_fontStyle, 0)]) {
						FontSTYLE.NORMAL -> createNormal
						FontSTYLE.BOLD -> createBold
						FontSTYLE.LIGHT -> createLight
						FontSTYLE.MEDIUM -> createMedium
					}.also { font = it }
				}
				autoColorSet = getBoolean(R.styleable.Button_autoColorSet, true)
				color = getColor(
					R.styleable.Button_android_textColor,
					getColor(context, R.color.titleTextColor)
				).also { color ->
					if (autoColorSet) setOnTouchListener { _, event ->
						when (event.action) {
							ACTION_DOWN, ACTION_HOVER_MOVE, ACTION_MOVE -> getColor(context, R.color.white)
							else -> if (isSelected) getColor(context, R.color.white) else color
						}.also { setTextColor(it) }
						false
					}
					when {
						!isEnabled -> getColor(context, R.color.disableTextColor)
						else -> color
					}.also { setTextColor(it) }
				}
			}.recycle()
		}
		typeface = font
	}
	
	var textString: String
		get() = if (text == null) "" else text.toString()
		set(value) {
			text = value
		}
	val isEmpty: Boolean
		get() = textString.isEmpty()
	val isNotEmpty: Boolean
		get() = textString.isNotEmpty()
	val makeEmpty: Unit
		get() = setText("")
}