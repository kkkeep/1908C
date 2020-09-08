package com.m.k.seetaoism.detail

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.m.k.mvp.utils.MvpUtils
import com.m.k.seetaoism.R


/*
 * created by Cherry on 2019-11-21
 **/
object IntegralWidget {


    @JvmStatic
    fun show(activity: FragmentActivity, integral: Int) {


        val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)

        val frameLayout = FrameLayout(activity)

        frameLayout.tag = "IntegralWidget"


        frameLayout.setBackgroundResource(R.drawable.ic_integral)

        val width = MvpUtils.dip2px(activity, 65f)
        val height = MvpUtils.dip2px(activity, 69f)

        val layoutParams = FrameLayout.LayoutParams(width, height)
        frameLayout.layoutParams = layoutParams

        val textView = TextView(activity)
        textView.setBackgroundColor(Color.BLACK)
        textView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

        textView.setTextColor(Color.WHITE)
        textView.gravity = Gravity.CENTER

        textView.text = "+$integral"


        frameLayout.addView(textView)

        val screenSize =  MvpUtils.getNotchScreenDensity(activity)

        val sw = screenSize[0];
        val sh = screenSize[1]

        val y = (sh * 0.54).toInt()
        val x = sw;


        frameLayout.x = x.toFloat()
        frameLayout.y = y.toFloat()
        viewGroup.addView(frameLayout)

        startAnimation(frameLayout)
    }



    private fun startAnimation(v : View){

        val objectAnimator = ObjectAnimator.ofFloat(v,"x",v.x,(v.x - v.layoutParams.width))

        objectAnimator.interpolator =  AccelerateDecelerateInterpolator()
        objectAnimator.duration = 600;


        objectAnimator.startDelay= 1000;
        objectAnimator.start()


        v.postDelayed({
            objectAnimator.apply {
                reverse()
                addListener(object : Animator.AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {}

                    override fun onAnimationEnd(animation: Animator?) {
                        val parent  = v.parent as ViewGroup
                        parent.removeView(v)

                    }
                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationStart(animation: Animator?) {}
                })
            }

        },2600)
    }
}
