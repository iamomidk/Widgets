package com.aperfectpolygon.widgets

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.getIntegerOrThrow
import androidx.core.view.isGone

class TextViewWithSuffixPrefix : LinearLayoutCompat {
	
	constructor(context: Context) : super(context) {
		null.initialize
	}
	
	constructor(
		context: Context,
		attr: AttributeSet,
	) : super(context, attr) {
		attr.initialize
	}
	
	constructor(
		context: Context,
		attr: AttributeSet?,
		defStyleAttr: Int,
	) : super(context, attr, defStyleAttr) {
		attr.initialize
	}
	
	private lateinit var txtMain: TextView
	private lateinit var txtSuffix: TextView
	private lateinit var txtPrefix: TextView
	private var drawableEnd = 0
	private var drawableStart = 0
	private var drawablePadding = 0
	var mainText: String? = ""
		set(value) {
			txtMain.text = value
			field = value
		}
	private var mainTextColor: Int = 0
		set(value) {
			txtMain.setTextColor(value)
			txtSuffix.isGone = txtSuffix.textString.isEmpty()
			txtPrefix.isGone = txtSuffix.textString.isEmpty()
			field = value
		}
	var suffixText: String? = ""
		set(value) {
			txtSuffix.text = value
			txtSuffix.isGone = value.isNullOrEmpty()
			field = value
		}
	var suffixTextColor: Int = 0
		set(value) {
			txtSuffix.setTextColor(value)
			field = value
		}
	var prefixText: String? = ""
		set(value) {
			txtPrefix.text = value
			txtPrefix.isGone = value.isNullOrEmpty()
			field = value
		}
	var prefixTextColor: Int = 0
		set(value) {
			txtPrefix.setTextColor(value)
			field = value
		}
	var mainTextSize: Int = 0
		set(value) {
			txtMain.setTextSize(TypedValue.COMPLEX_UNIT_PX, value.toFloat())
			field = value
		}
	var suffixTextSize: Int = 0
		set(value) {
			txtSuffix.setTextSize(TypedValue.COMPLEX_UNIT_PX, value.toFloat())
			field = value
		}
	var prefixTextSize: Int = 0
		set(value) {
			txtPrefix.setTextSize(TypedValue.COMPLEX_UNIT_PX, value.toFloat())
			field = value
		}
	
	private val AttributeSet?.initialize: View
		get() = inflate(
			context,
			R.layout.custom_view_tv_with_suffix,
			this@TextViewWithSuffixPrefix
		).apply {
			txtMain = findViewById(R.id.txt_mainText)
			txtSuffix = findViewById(R.id.txt_suffix)
			txtPrefix = findViewById(R.id.txt_prefix)
			txtMain.isSelected = true
			context.obtainStyledAttributes(this@initialize, R.styleable.TextViewWithSuffixPrefix).apply {
				createNormal.also {
					txtMain.typeface = it
					txtSuffix.typeface = it
					txtPrefix.typeface = it
				}
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_fontStyle)) {
					when (FontSTYLE.values()[getIntegerOrThrow(R.styleable.TextViewWithSuffixPrefix_fontStyle)]) {
						FontSTYLE.NORMAL -> createNormal
						FontSTYLE.BOLD -> createBold
						FontSTYLE.LIGHT -> createLight
						FontSTYLE.MEDIUM -> createMedium
					}.also {
						txtMain.typeface = it
						txtSuffix.typeface = it
						txtPrefix.typeface = it
					}
				}
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_prefixTextColor))
					getColor(
						R.styleable.TextViewWithSuffixPrefix_prefixTextColor,
						ContextCompat.getColor(
							context, R.color.subTitleTextColor
						)
					).also { prefixTextColor = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_android_drawableEnd))
					getResourceId(R.styleable.TextViewWithSuffixPrefix_android_drawableEnd, 0)
						.also { drawableEnd = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_android_drawableStart))
					getResourceId(R.styleable.TextViewWithSuffixPrefix_android_drawableStart, 0)
						.also { drawableStart = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_android_drawablePadding)) getDimensionPixelSize(
					R.styleable.TextViewWithSuffixPrefix_android_drawablePadding, 0
				).also { drawablePadding = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_android_textSize)) getDimensionPixelSize(
					R.styleable.TextViewWithSuffixPrefix_android_textSize, com.intuit.sdp.R.dimen._10sdp
				).also { mainTextSize = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_suffixTextSize)) getDimensionPixelSize(
					R.styleable.TextViewWithSuffixPrefix_suffixTextSize, com.intuit.sdp.R.dimen._6sdp
				).also { suffixTextSize = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_prefixTextSize)) getDimensionPixelSize(
					R.styleable.TextViewWithSuffixPrefix_prefixTextSize, com.intuit.sdp.R.dimen._6sdp
				).also { prefixTextSize = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_prefixText)) getString(
					R.styleable.TextViewWithSuffixPrefix_prefixText
				).also { prefixText = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_suffixText)) getString(
					R.styleable.TextViewWithSuffixPrefix_suffixText
				).also { suffixText = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_android_text))
					getString(R.styleable.TextViewWithSuffixPrefix_android_text).also { mainText = it }
				
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_android_textColor))
					getColor(
						R.styleable.TextViewWithSuffixPrefix_android_textColor,
						ContextCompat.getColor(context, R.color.titleTextColor)
					).also { mainTextColor = it }
				if (hasValue(R.styleable.TextViewWithSuffixPrefix_suffixTextColor))
					getColor(
						R.styleable.TextViewWithSuffixPrefix_suffixTextColor,
						ContextCompat.getColor(context, R.color.subTitleTextColor)
					).also { suffixTextColor = it }
				
				txtMain.apply {
					setCompoundDrawablesRelativeWithIntrinsicBounds(drawableStart, 0, drawableEnd, 0)
					text = mainText
					setTextColor(mainTextColor)
				}
				txtSuffix.apply {
					text = suffixText
					setTextColor(suffixTextColor)
				}
				txtPrefix.apply {
					text = prefixText
					setTextColor(prefixTextColor)
				}
				txtSuffix.isGone = txtSuffix.isEmpty
				txtPrefix.isGone = txtPrefix.isEmpty
			}.recycle()
		}
	
	fun setText(suffix: String, mainText: String, prefix: String) {
		this.suffixText = suffix
		this.mainText = mainText
		this.prefixText = prefix
		txtSuffix.text = suffix
		txtMain.text = mainText
		txtPrefix.text = prefix
		txtSuffix.isGone = txtSuffix.isEmpty
		txtPrefix.isGone = txtPrefix.isEmpty
	}
	
	fun setText(suffix: Int, mainText: Int, prefix: Int) {
		this.suffixText = context.getString(suffix)
		this.mainText = context.getString(mainText)
		this.prefixText = context.getString(prefix)
		txtSuffix.setText(suffix)
		txtMain.setText(mainText)
		txtPrefix.setText(prefix)
		txtSuffix.isGone = txtSuffix.isEmpty
		txtPrefix.isGone = txtPrefix.isEmpty
	}
	
	fun setTextWithSuffix(suffix: String, mainText: String) {
		this.suffixText = suffix
		this.mainText = mainText
		txtMain.text = mainText
		txtSuffix.text = suffix
		txtSuffix.isGone = txtSuffix.isEmpty
		txtPrefix.isGone = txtPrefix.isEmpty
	}
	
	fun setTextWithPrefix(prefix: String, mainText: String) {
		this.prefixText = prefix
		this.mainText = mainText
		txtMain.text = mainText
		txtPrefix.text = prefix
		txtSuffix.isGone = txtSuffix.isEmpty
		txtPrefix.isGone = txtPrefix.isEmpty
	}
	
	fun setMainGravity(gravity: Int) {
		txtMain.gravity = gravity
	}
	
	fun paintStrikeThrough(
		color: Int = when {
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> context.getColor(R.color.disableTextColor)
			else -> resources.getColor(R.color.disableTextColor)
		},
		justColor: Boolean = false,
		onSuffixAndPrefix: Boolean = false,
		onSuffix: Boolean = false,
		onPrefix: Boolean = false,
	) {
		if (onSuffix) {
			if (!justColor) txtSuffix.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
			txtSuffix.setTextColor(color)
		}
		if (onPrefix) {
			if (!justColor) txtPrefix.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
			txtPrefix.setTextColor(color)
		}
		if (onSuffixAndPrefix) {
			if (!justColor) txtSuffix.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
			txtSuffix.setTextColor(color)
			if (!justColor) txtPrefix.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
			txtPrefix.setTextColor(color)
		}
		txtMain.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
		txtMain.setTextColor(color)
	}
	
	fun clearStrikeThrough(
		txtColor: Int = when {
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> context.getColor(R.color.titleTextColor)
			else -> resources.getColor(R.color.titleTextColor)
		}
	) {
		txtSuffix.paintFlags = 0
		txtSuffix.setTextColor(txtColor)
		txtPrefix.paintFlags = 0
		txtPrefix.setTextColor(txtColor)
		txtMain.paintFlags = 0
		txtMain.setTextColor(txtColor)
	}
	
	fun setTextColor(@ColorInt color: Int) {
		color.also {
			txtMain.setTextColor(it)
			txtSuffix.setTextColor(it)
			txtPrefix.setTextColor(it)
		}
	}
	
	fun setCompoundDrawablesRelativeWithIntrinsicBounds(start: Int, top: Int, end: Int, bottom: Int) {
		txtMain.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
	}
	
	fun targetReached(
		vararg targets: Double,
		target: Double,
		lastPrice: Double,
		@ColorRes txtColor: Int = R.color.titleTextColor,
	) = txtMain.apply {
		val isDone = when {
			targets[0] < targets[1] -> lastPrice > target
			else -> lastPrice < target
		}
		paintFlags = when (isDone) {
			true -> {
				setTextColor(
					when {
						Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> context.getColor(R.color.disableTextColor)
						else -> resources.getColor(R.color.disableTextColor)
					}
				)
				Paint.STRIKE_THRU_TEXT_FLAG
			}
			false -> {
				setTextColor(
					when {
						Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> context.getColor(txtColor)
						else -> resources.getColor(txtColor)
					}
				)
				0
			}
		}
	}
}