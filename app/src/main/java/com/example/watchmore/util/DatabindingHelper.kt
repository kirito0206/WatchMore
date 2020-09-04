package com.example.watchmore.util

import android.os.Handler
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.watchmore.MyApplication
import com.example.watchmore.R
import com.example.watchmore.model.bean.animebean.AnimeData
import com.example.watchmore.model.bean.animebean.Comment
import com.example.watchmore.model.bean.questionbean.QuestionBean
import com.example.watchmore.model.bean.recommendbean.RecommendBean
import com.example.watchmore.model.bean.userbean.UserBean
import com.example.watchmore.ui.adapter.GridAdapter
import com.example.watchmore.ui.adapter.RecyclerCommentAdapter
import com.example.watchmore.ui.adapter.RecyclerQuestionAdapter
import com.example.watchmore.ui.adapter.RecyclerRecommendAdapter
import com.example.watchmore.ui.question.QuestionViewModel
import com.example.watchmore.ui.recommend.RecommendViewModel
import com.example.watchmore.ui.view.NineGridLayout
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jcodecraeer.xrecyclerview.XRecyclerView.LoadingListener


class DatabindingHelper {

    companion object{
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(iv: ImageView, url: String?) {
            if (url == null){
                iv.setImageResource(R.drawable.head_image_default)
                return
            }
            Glide.with(iv.context).load(url)
                .into(iv)
        }

        @BindingAdapter("imageStar")
        @JvmStatic
        fun loadImage(iv: ImageView, isLike : Boolean) {
            if (isLike){
                iv.setImageResource(R.drawable.star)
            }else
                iv.setImageResource(R.drawable.unstar)
        }

        @BindingAdapter("resId")
        @JvmStatic
        fun loadMipmapResource(iv: ImageView, resId: Int) {
            iv.setImageResource(resId)
        }

        @BindingAdapter("gridlist")
        @JvmStatic
        fun loadGridView(gridView : GridView,animesList:MutableList<AnimeData>?) {
            if (animesList == null){
                debug("animelist : null")
                return
            }
            gridView.adapter = GridAdapter(animesList)
        }

        @BindingAdapter("commentslist")
        @JvmStatic
        fun loadRecyclerView(recyclerView: XRecyclerView,commentsList:MutableList<Comment>?) {
            if (commentsList == null){
                debug("commentsList : null")
                return
            }
            val layoutManager = LinearLayoutManager(recyclerView.context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = RecyclerCommentAdapter(commentsList)

            //设置是否允许下拉刷新
            recyclerView.setPullRefreshEnabled(false)
            //设置是否允许上拉加载
            recyclerView.setLoadingMoreEnabled(true)
            recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
            recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader)

            recyclerView.setLoadingListener(object : LoadingListener {
                //下拉刷新
                override fun onRefresh() {
                    //当下拉刷新的时候，重新获取数据，所有curr要变回0，并且把集合list清空
                    Handler().postDelayed(Runnable {
                        //curr = 1 //当前页码
                        /**加载数据处理 */
                        recyclerView.refreshComplete()
                    }, 2000)
                }

                //上拉加载
                override fun onLoadMore() {
                    Handler().postDelayed(Runnable {
                        //curr++ //当前页码
                        /**加载数据处理 */
                        /**加载数据处理 */
                        /**加载数据处理 */
                        /**加载数据处理 */
                        recyclerView.loadMoreComplete()
                    }, 2000)
                }
            })
        }

        @BindingAdapter(value = ["questionslist", "userslist","viewmodel"], requireAll = true)
        @JvmStatic
        fun loadQuestionRecyclerView(recyclerView: XRecyclerView,questionsList:MutableList<QuestionBean>?,usersList:MutableList<UserBean>?,viewModel: QuestionViewModel) {
            if (usersList == null){
                debug("userlist : null")
                return
            }
            if (questionsList == null){
                debug("questionsList : null")
                return
            }
            val layoutManager = LinearLayoutManager(recyclerView.context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = RecyclerQuestionAdapter(questionsList,usersList)

            //设置是否允许下拉刷新
            recyclerView.setPullRefreshEnabled(true)
            //设置是否允许上拉加载
            recyclerView.setLoadingMoreEnabled(false)
            recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
            recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader)

            recyclerView.setLoadingListener(object : LoadingListener {
                //下拉刷新
                override fun onRefresh() {
                    //当下拉刷新的时候，重新获取数据，所有curr要变回0，并且把集合list清空
                    Handler().postDelayed(Runnable {
                        viewModel.page++
                        viewModel.loadDatas()
                        /**加载数据处理 */
                        recyclerView.refreshComplete()
                    }, 2000)
                }

                //上拉加载
                override fun onLoadMore() {
                    Handler().postDelayed(Runnable {
                        //curr++ //当前页码
                        /**加载数据处理 */
                        /**加载数据处理 */
                        /**加载数据处理 */
                        /**加载数据处理 */
                        recyclerView.loadMoreComplete()
                    }, 2000)
                }
            })
        }


        @BindingAdapter(value = ["recommendlist", "userslist","viewmodel"], requireAll = true)
        @JvmStatic
        fun loadRecommendRecyclerView(recyclerView: XRecyclerView,recommendList:MutableList<RecommendBean>?,usersList:MutableList<UserBean>?,viewModel: RecommendViewModel) {
            if (usersList == null){
                debug("userlist : null")
                return
            }
            if (recommendList == null){
                debug("recommendList : null")
                return
            }
            val layoutManager = LinearLayoutManager(recyclerView.context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = RecyclerRecommendAdapter(recommendList,usersList)

            //设置是否允许下拉刷新
            recyclerView.setPullRefreshEnabled(true)
            //设置是否允许上拉加载
            recyclerView.setLoadingMoreEnabled(false)
            recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
            recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader)

            recyclerView.setLoadingListener(object : LoadingListener {
                //下拉刷新
                override fun onRefresh() {
                    //当下拉刷新的时候，重新获取数据，所有curr要变回0，并且把集合list清空
                    Handler().postDelayed(Runnable {
                        viewModel.page++
                        viewModel.loadDatas()
                        /**加载数据处理 */
                        recyclerView.refreshComplete()
                    }, 2000)
                }

                //上拉加载
                override fun onLoadMore() {
                    Handler().postDelayed(Runnable {
                        //curr++ //当前页码
                        /**加载数据处理 */
                        /**加载数据处理 */
                        /**加载数据处理 */
                        /**加载数据处理 */
                        recyclerView.loadMoreComplete()
                    }, 2000)
                }
            })
        }

        @BindingAdapter(value = ["myquestionslist", "userlist"], requireAll = true)
        @JvmStatic
        fun loadMyQuestionRecyclerView(recyclerView: XRecyclerView,questionsList:MutableList<QuestionBean>?,usersList:MutableList<UserBean>?) {
            if (usersList == null){
                debug("userlist : null")
                return
            }
            if (questionsList == null){
                debug("questionsList : null")
                return
            }
            val layoutManager = LinearLayoutManager(recyclerView.context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = RecyclerQuestionAdapter(questionsList,usersList)

            //设置是否允许下拉刷新
            recyclerView.setPullRefreshEnabled(false)
            //设置是否允许上拉加载
            recyclerView.setLoadingMoreEnabled(false)
            recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
            recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader)
        }

        @BindingAdapter("urlList")
        @JvmStatic
        fun loadNineGridLayout(nineGridLayout: NineGridLayout,urlList: List<String>?){
            if (urlList == null)
                return
            nineGridLayout.setIsShowAll(false)
            nineGridLayout.setSpacing(5.0f)
            nineGridLayout.setUrlList(urlList)
        }

        @BindingAdapter("userId")
        @JvmStatic
        fun canVisible(view: TextView,userId : Int){
            var userId0 = SPUtils.getData(view.context,SPUtils.USER_FILE,SPUtils.UserKey.USER_USERID,-1)
            if (userId0 == userId)
                view.visibility = View.VISIBLE
            else
                view.visibility = View.GONE
        }
    }
}