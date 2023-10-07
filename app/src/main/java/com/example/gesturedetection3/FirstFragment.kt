package com.example.gesturedetection3

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GestureDetectorCompat
import androidx.navigation.fragment.findNavController
import com.example.gesturedetection3.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDetector: GestureDetectorCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        mDetector = GestureDetectorCompat(requireContext(), MyGestureListener())
        val rootView = binding.root
        rootView.setOnTouchListener { view, motionEvent ->
            mDetector.onTouchEvent(motionEvent)
            Log.d("APPLE", "Touch: " + motionEvent.x + " " + motionEvent.y)
            true
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onLongPress(e: MotionEvent) {
            Log.d("APPLE", "onLongPress $e")
            super.onLongPress(e)
        }

        override fun onScroll(
            ev1: MotionEvent?, ev2: MotionEvent, distanceX: Float, distanceY: Float
        ): Boolean {
            Log.d("APPLE", "onScroll: $ev1 \n $ev2")
            doIt(ev1, ev2)
            return super.onScroll(ev1, ev2, distanceX, distanceY)
        }

        override fun onFling(
            ev1: MotionEvent?, ev2: MotionEvent, velocityX: Float, velocityY: Float
        ): Boolean {
            Log.d("APPLE", "onFling: $ev1 \n $ev2")
            doIt(ev1, ev2)
            return super.onFling(ev1, ev2, velocityX, velocityY)
        }


        private fun doIt(ev1: MotionEvent?, ev2: MotionEvent) {
            if (ev1 != null) {
                val xDiff = ev1.x - ev2.x
                if (xDiff > 0) {
                    findNavController() // inner keyword on MyGesture Listener
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
            }
        }

    }
}