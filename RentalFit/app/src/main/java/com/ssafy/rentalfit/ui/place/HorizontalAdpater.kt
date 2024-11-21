package com.ssafy.rentalfit.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R

private const val TAG = "HorizontalAdpater_싸피"

class HorizontalAdapter(private val items: List<Place>) :
    RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>() {

    var onItemClickListener: ((Place) -> Unit)? = null

    class HorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.itemImage)
        val title: TextView = itemView.findViewById(R.id.itemTitle)
        val firstRowTimeLabels: LinearLayout = itemView.findViewById(R.id.firstRowTimeLabels)
        val firstRowBlocks: LinearLayout = itemView.findViewById(R.id.firstRowBlocks)
        val secondRowTimeLabels: LinearLayout = itemView.findViewById(R.id.secondRowTimeLabels)
        val secondRowBlocks: LinearLayout = itemView.findViewById(R.id.secondRowBlocks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_place_item_horizontal, parent, false)
        return HorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name
        holder.image.setImageResource(holder.itemView.context.resources.getIdentifier(item.img, "drawable", holder.itemView.context.packageName))

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(items[position])
        }

        initSchedule(holder)
    }

    override fun getItemCount(): Int = items.size

    private fun initSchedule(holder: HorizontalViewHolder) {
        holder.firstRowTimeLabels.removeAllViews()
        holder.firstRowBlocks.removeAllViews()
        holder.secondRowTimeLabels.removeAllViews()
        holder.secondRowBlocks.removeAllViews()

        val distanceBetTime = 24
        val distanceBetBlock = 12

        // 9시 ~ 3시 시간 및 블록 추가
        for (hour in 9..14) {
            // 시간 라벨 추가
            val timeLabel = TextView(holder.itemView.context).apply {
                text = hour.toString()
                textSize = 12f
                setTextColor(ContextCompat.getColorStateList(holder.itemView.context, R.color.oatmeal_main))
                setPadding(0, 0, distanceBetTime, 0)
            }
            holder.firstRowTimeLabels.addView(timeLabel)

            // 해당 시간에 대한 블록 2개 추가 (30분 단위)
            repeat(2) {
                val block = View(holder.itemView.context).apply {
                    layoutParams = LinearLayout.LayoutParams(20, 30).apply {
                        id = hour * 100 + it*30 // 아이디는 900, 930......1700,1730
                        weight = 1f
                        marginStart = distanceBetBlock
                        marginEnd = 0
                    }
                    setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grey_main))
                }
//                Log.d(TAG, "id: ${block.id}")
                holder.firstRowBlocks.addView(block)
            }
        }

        // 3시 ~ 6시 시간 및 블록 추가
        for (hour in 15..18) {
            // 시간 라벨 추가
            val timeLabel = TextView(holder.itemView.context).apply {
                text = hour.toString()
                textSize = 12f
                setTextColor(ContextCompat.getColorStateList(holder.itemView.context, R.color.oatmeal_main))
                setPadding(0, 0, distanceBetTime - 4, 0)
            }
            holder.secondRowTimeLabels.addView(timeLabel)

            // 해당 시간에 대한 블록 2개 추가 (30분 단위)
            repeat(2) {
                val block = View(holder.itemView.context).apply {
                    layoutParams = LinearLayout.LayoutParams(20, 30).apply {
                        id = hour * 100 + it*30
                        weight = 1f
                        marginStart = distanceBetBlock
                        marginEnd = 0
                    }
                    setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grey_main))
                }
                holder.secondRowBlocks.addView(block)
            }
        }

        // 첫 번째 줄의 블록 개수와 맞추기 위해 보이지 않는 블록 추가
        val firstRowBlockCount = holder.firstRowBlocks.childCount
        val secondRowBlockCount = holder.secondRowBlocks.childCount

        if (firstRowBlockCount > secondRowBlockCount) {
            repeat(firstRowBlockCount - secondRowBlockCount) {
                val invisibleBlock = View(holder.itemView.context).apply {
                    layoutParams = LinearLayout.LayoutParams(20, 30).apply {
                        weight = 1f
                        marginStart = distanceBetBlock
                        marginEnd = 0
                    }
                    visibility = View.INVISIBLE // 보이지 않는 블록
                }
                holder.secondRowBlocks.addView(invisibleBlock)
            }
        }

        drawSchedule(holder)


    }

    fun drawSchedule(holder: HorizontalViewHolder){
        for(item in holder.firstRowBlocks){
            if (item.id <= 1230){
                item.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.neon_main))
            }
        }
        for(item in holder.secondRowBlocks){
            if (item.id > 1700){
                item.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.neon_main))
            }
        }
    }
}
