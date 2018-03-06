package com.solmin.sample.components.list

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.facebook.common.executors.UiThreadImmediateExecutorService
import com.facebook.datasource.BaseDataSubscriber
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import com.solmin.sample.R
import com.solmin.sample.components.list.pojo.DataItem
import java.text.DecimalFormat


/**
 * Itemを表示するためのAdapter.
 * Created by h_sol on 2018/02/27.
 */
class ItemAdapter(private val context: Context, private val itemList: List<DataItem>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val TAG = ItemAdapter::class.simpleName
    private val prefetchSubscriber = object : BaseDataSubscriber<Void>() {
        override fun onFailureImpl(dataSource: DataSource<Void>?) {
            Log.v(TAG, "onFailureImpl")
        }

        override fun onNewResultImpl(dataSource: DataSource<Void>?) {
            Log.v(TAG, "onNewResultImpl")
        }
    }
    private val progressBarDrawable: ProgressBarDrawable = getProgressBarDrawable()

    init {
        // 先読み処理: Adapterが持つ全アイテムの画像をダウンロードさせる
        // 1回で取得する画像数に応じて前取得させるか、それともユーザーのスクロールに応じて取得させるかチューニングが必要。
        // 一旦サンプルでは、画像数が多くないので前取得にしておく
        itemList
                .map { Fresco.getImagePipeline().prefetchToDiskCache(ImageRequest.fromUri(it.photo), null) }
                .forEach { it.subscribe(prefetchSubscriber, UiThreadImmediateExecutorService.getInstance()) }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val layoutView = LayoutInflater.from(parent?.context).inflate(R.layout.view_single_item, parent, false)

        return ItemViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        val item: DataItem = itemList[position]

        if (holder == null) {
            return
        }

        // 画像
        holder.itemImageView.setImageURI(item.photo)
        holder.itemImageView.hierarchy.setProgressBarImage(progressBarDrawable)

        holder.itemClickArea.setOnClickListener {
            // animation
            YoYo.with(Techniques.Tada)
                    .playOn(holder.itemView)

            Snackbar.make(it, "clicked position $position", Snackbar.LENGTH_SHORT).show()
        }

        // 価格
        val formatter = DecimalFormat("$ #,###")
        val formattedString: String = formatter.format(item.price)
        holder.priceTextView.text = formattedString

        // newバッジ
        holder.newBadge.visibility = when (item.status) {
            ApiConstant.STATUS_NEW -> View.VISIBLE
            else -> View.GONE
        }

        // like
        holder.likeTextView.text = item.numLikes.toString()

        // comment
        holder.commentCountTextView.text = item.numComments.toString()

        // title
        holder.descriptionTextView.text = item.title

        // お気に入りボタン
        var isLiked = false
        holder.favoriteBtn.setOnClickListener {
            val color = if (!isLiked) {
                it.context.resources.getColor(R.color.favBtnActive)
            } else {
                0
            }

            YoYo.with(Techniques.ZoomIn)
                    .duration(300)
                    .onStart {
                        holder.favoriteBtn.setColorFilter(color)
                        isLiked = !isLiked
                    }
                    .playOn(it)
        }
    }

    /**
     * ProgressBarのデザインを作る
     */
    private fun getProgressBarDrawable() : ProgressBarDrawable {
        val progressBarDrawable = ProgressBarDrawable()
        progressBarDrawable.color = context.resources.getColor(R.color.colorAccent)
        progressBarDrawable.backgroundColor = context.resources.getColor(R.color.gray)
        progressBarDrawable.radius = context.resources.getDimensionPixelSize(R.dimen.progress_bar_radius)

        return progressBarDrawable
    }

    /**
     * ViewHolder
     */
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val newBadge: ImageView = itemView.findViewById(R.id.newBadge)
            val itemImageView: SimpleDraweeView = itemView.findViewById(R.id.itemImageView)
            val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
            val likeTextView: TextView = itemView.findViewById(R.id.likeCount)
            val commentCountTextView: TextView = itemView.findViewById(R.id.commentCount)
            val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
            val itemClickArea: FrameLayout = itemView.findViewById(R.id.itemClickArea)
            val favoriteBtn: ImageView = itemView.findViewById(R.id.favoriteBtn)
    }
}