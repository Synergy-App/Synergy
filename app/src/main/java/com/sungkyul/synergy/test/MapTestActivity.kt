package com.sungkyul.synergy.test

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityMapTestBinding
import com.sungkyul.synergy.utils.MapPiece

class MapTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSet = ArrayList<MapPiece>()
        /*for(y in 0..<100) {
            for(x in 0..<100) {
                dataSet.add(
                    MapPiece(
                        if((x+y)%2 == 0) R.drawable.card else R.drawable.charge,
                        Rect(x*400, y*400, (x+1)*400, (y+1)*400)
                    )
                )
            }
        }*/
        for(y in 0..<3) {
            for(x in 0..<3) {
                dataSet.add(
                    MapPiece(
                        if((x+y)%2 == 0) R.drawable.blog_image1 else R.drawable.blog_image2,
                        Rect(x*1000, y*1000, (x+1)*1000, (y+1)*1000)
                    )
                )
            }
        }
        /*dataSet.add(
            MapPiece(
                R.drawable.blog_image1,
                Rect(0, 0, 3000, 3000)
            )
        )*/

        binding.mapView.mapPieces = dataSet
    }
}
