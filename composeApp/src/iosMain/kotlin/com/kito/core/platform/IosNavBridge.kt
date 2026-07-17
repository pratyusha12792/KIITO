package com.kito.core.platform

object IosNavBridge {

    var onStateChange: ((Int, Boolean) -> Unit)? = null

    var onTabSelected: ((Int) -> Unit)? = null

    fun publishState(index: Int, visible: Boolean) {
        onStateChange?.invoke(index, visible)
    }

    fun selectTab(index: Int) {
        onTabSelected?.invoke(index)
    }
}
