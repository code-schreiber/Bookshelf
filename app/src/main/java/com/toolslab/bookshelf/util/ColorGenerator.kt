package com.toolslab.bookshelf.util

import android.graphics.Color
import android.support.annotation.VisibleForTesting
import java.util.*
import javax.inject.Inject

class ColorGenerator @Inject constructor() {

    fun generate(random: Random) = Color.argb(ALPHA, random.nextInt(MAX_RGB), random.nextInt(MAX_RGB), random.nextInt(MAX_RGB))

    companion object {

        @VisibleForTesting
        const val MAX_RGB = 256

        @VisibleForTesting
        const val ALPHA = 128

    }

}
