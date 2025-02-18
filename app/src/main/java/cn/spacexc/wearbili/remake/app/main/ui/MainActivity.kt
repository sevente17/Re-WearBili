package cn.spacexc.wearbili.remake.app.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import cn.spacexc.wearbili.remake.app.main.dynamic.ui.DynamicViewModel
import cn.spacexc.wearbili.remake.app.main.profile.ui.ProfileViewModel
import cn.spacexc.wearbili.remake.app.main.recommend.ui.RecommendViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by XC-Qan on 2023/4/6.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val recommendViewModel by viewModels<RecommendViewModel>()
    private val dynamicViewModel by viewModels<DynamicViewModel>()
    private val profileViewModel by viewModels<ProfileViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //throw RuntimeException("这是一个用于测试的异常")
        profileViewModel.getProfile()
        setContent {
            MainActivityScreen(
                context = this,
                recommendViewModel = recommendViewModel,
                dynamicViewModel = dynamicViewModel,
                profileScreenState = profileViewModel.screenState,
                onProfileRetry = {
                    profileViewModel.getProfile()
                }
            )
        }
    }
}