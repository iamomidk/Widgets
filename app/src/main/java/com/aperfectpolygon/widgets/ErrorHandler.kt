package com.aperfectpolygon.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat

class ErrorHandler : LinearLayoutCompat {
	
	constructor(context: Context) : super(context) {
		initialize(null)
	}
	
	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
		initialize(attrs)
	}
	
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
		context,
		attrs,
		defStyleAttr
	) {
		initialize(attrs)
	}
	
	private lateinit var imageView: AppCompatImageView
	private lateinit var textView: TextView
	
	private fun initialize(attrs: AttributeSet?) =
		inflate(context, R.layout.custom_view_not_found, this).apply {
			imageView = findViewById(R.id.imageView)
			textView = findViewById(R.id.text)
			context.obtainStyledAttributes(attrs, R.styleable.ErrorHandler).apply {
				textView.text = getString(R.styleable.ErrorHandler_android_text)
				imageView.setImageResource(
					getResourceId(
						R.styleable.ErrorHandler_android_src, R.drawable.vtr_not_found
					)
				)
			}.recycle()
		}
	
	fun setImageResource(@DrawableRes drawableRes: Int) = imageView.setImageResource(drawableRes)
	
	fun setText(text: String?) {
		textView.text = text
	}
	
	fun setText(@StringRes text: Int) = textView.setText(text)
	
	override fun setOnClickListener(l: OnClickListener?) = imageView.setOnClickListener(l)
}