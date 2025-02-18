package cn.spacexc.wearbili.remake.app.cache.list

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cn.spacexc.wearbili.remake.R
import cn.spacexc.wearbili.remake.common.ui.TitleBackground
import cn.spacexc.wearbili.remake.common.ui.VideoCacheCard
import cn.spacexc.wearbili.remake.common.ui.wearBiliAnimateContentPlacement

/**
 * Created by XC-Qan on 2023/9/9.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Activity.CacheListScreen(
    viewModel: CacheListViewModel
) {
    val completedTasks by viewModel.completedTasks.collectAsState(initial = emptyList())
    val unCompletedTasks by viewModel.unCompletedTasks.collectAsState(initial = emptyList())
    TitleBackground(title = "视频缓存", onBack = ::finish) {
        if (completedTasks.isEmpty() && unCompletedTasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_empty_box),
                        contentDescription = "Empty...",
                        modifier = Modifier.fillMaxWidth()
                        //.fillMaxWidth(0.3f)
                        //.aspectRatio(1f)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "什么都没有啊")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (unCompletedTasks.isNotEmpty()) {
                    item(key = "text1") {
                        Text(
                            text = "正在缓存",
                            style = MaterialTheme.typography.h2,
                            color = Color.White
                        )
                    }
                    unCompletedTasks.forEach { task ->
                        item(key = task.videoCid) {
                            VideoCacheCard(
                                cacheInfo = task,
                                modifier = Modifier.wearBiliAnimateContentPlacement(this)
                            )
                        }
                    }
                }
                if (completedTasks.isNotEmpty()) {
                    item(key = "text2") {
                        Text(
                            text = "已缓存",
                            style = MaterialTheme.typography.h2,
                            color = Color.White
                        )
                    }
                    completedTasks.forEach { task ->
                        item(key = task.videoCid) {
                            VideoCacheCard(
                                cacheInfo = task,
                                modifier = Modifier.wearBiliAnimateContentPlacement(this)
                            )
                        }
                    }
                }
            }
        }
    }
} 