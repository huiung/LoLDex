package com.hu.loldex.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

/*
 * Designed and developed by 2023 huiung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
object ComposeUtils {

    @Composable
    fun DisposableEffectLifecycleObserver(
        onCreate: () -> Unit = {},
        onStart: () -> Unit = {},
        onResume: () -> Unit = {},
        onPause: () -> Unit = {},
        onStop: () -> Unit = {},
        onDestroy: () -> Unit = {},
    ) {
        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(Unit) {
            val observer = LifecycleEventObserver { source, event ->
                when (event) {
                    Lifecycle.Event.ON_DESTROY -> {
                        onDestroy()
                    }

                    Lifecycle.Event.ON_START -> {
                        onStart()
                    }

                    Lifecycle.Event.ON_STOP -> {
                        onStop()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        onResume()
                    }

                    Lifecycle.Event.ON_PAUSE -> {
                        onPause()
                    }

                    Lifecycle.Event.ON_CREATE -> {
                        onCreate()
                    }

                    else -> {}
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }

}