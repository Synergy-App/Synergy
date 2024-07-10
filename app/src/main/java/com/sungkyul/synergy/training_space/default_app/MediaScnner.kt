package com.sungkyul.synergy.training_space.default_app
import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import android.text.TextUtils

/**
 * 이미지 저장 후 미디어 스캐닝을 수행해줄 때 사용하는 유틸 클래스
 */
class MediaScanner private constructor(private val mContext: Context) {

    private var mFilePath: String = ""
    lateinit var mMediaScanner: MediaScannerConnection

    init {
        val mediaScanClient = object : MediaScannerConnection.MediaScannerConnectionClient {
            override fun onMediaScannerConnected() {
                mMediaScanner.scanFile(mFilePath, null)
            }

            override fun onScanCompleted(path: String, uri: Uri) {
                println("::::MediaScan Success::::")
                mMediaScanner.disconnect()
            }
        }
        mMediaScanner = MediaScannerConnection(mContext, mediaScanClient)
    }

    fun mediaScanning(path: String) {
        if (TextUtils.isEmpty(path)) return
        mFilePath = path
        if (!mMediaScanner.isConnected) mMediaScanner.connect()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var mMediaInstance: MediaScanner? = null

        fun getInstance(context: Context?): MediaScanner? {
            if (context == null) return null
            if (mMediaInstance == null) {
                synchronized(MediaScanner::class.java) {
                    if (mMediaInstance == null) {
                        mMediaInstance = MediaScanner(context)
                    }
                }
            }
            return mMediaInstance
        }

        fun releaseInstance() {
            mMediaInstance = null
        }
    }
}