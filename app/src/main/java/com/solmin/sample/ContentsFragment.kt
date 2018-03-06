package com.solmin.sample


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.logansquare.LoganSquare
import com.solmin.sample.components.list.ApiConstant
import com.solmin.sample.components.list.ItemAdapter
import com.solmin.sample.components.list.pojo.Response
import java.io.InputStream

/**
 * 商品リストを表示する画面。
 */
class ContentsFragment : Fragment() {
    enum class Type {
        NATURE,
        ANIMALS,
        FOOD
    }

    private var mType: Type? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mType = arguments.getSerializable(ARG_TYPE) as Type?
            Log.d(TAG, "type = $mType")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_contents, container, false)
        val recyclerView = v.findViewById<RecyclerView>(R.id.contentsRv)

        // JSONをrawリソースから取得する
        val json: InputStream? = when (mType) {
            Type.NATURE -> resources.openRawResource(R.raw.nature)
            Type.ANIMALS -> resources.openRawResource(R.raw.animals)
            Type.FOOD -> resources.openRawResource(R.raw.food)
            else -> null
        }

        // pojo classにparseする
        val response: Response = LoganSquare.parse(json, Response::class.java)
        Log.d(TAG, "parse result= $response")

        if (response.result == ApiConstant.OK) {
            recyclerView.adapter = ItemAdapter(response.data)
        }

        return v
    }

    companion object {
        private val TAG = ContentsFragment::class.simpleName
        private const val ARG_TYPE = "argType"

        fun newInstance(type: Type): ContentsFragment {
            val fragment = ContentsFragment()
            val args = Bundle()
            args.putSerializable(ARG_TYPE, type)
            fragment.arguments = args
            return fragment
        }
    }

}