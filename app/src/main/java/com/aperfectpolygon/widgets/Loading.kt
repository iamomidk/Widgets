package com.aperfectpolygon.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView

class Loading : LinearLayoutCompat {
	
	constructor(context: Context) : super(context) {
		initialize
	}
	
	constructor(
		context: Context,
		attrs: AttributeSet?,
	) : super(context, attrs) {
		initialize
	}
	
	constructor(
		context: Context,
		attrs: AttributeSet?,
		defStyleAttr: Int,
	) : super(context, attrs, defStyleAttr) {
		initialize
	}
	
	private lateinit var loading: LottieAnimationView
	private val initialize: View
		get() = inflate(context, R.layout.loading, this).apply {
			loading = findViewById(R.id.lottie_animation)
		}
	val startLoading: Int
		get() {
			loading.playAnimation()
			isVisible = true
			return visibility
		}
	val stopLoading: Int
		get() {
			loading.cancelAnimation()
			isGone = true
			return visibility
		}
}