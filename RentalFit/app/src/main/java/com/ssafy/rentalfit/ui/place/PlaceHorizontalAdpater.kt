package com.ssafy.rentalfit.ui.place

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.iterator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.data.model.dto.Place
import com.ssafy.rentalfit.databinding.ListEquipItemHorizontalBinding
import com.ssafy.rentalfit.databinding.ListPlaceItemHorizontalBinding
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private const val TAG = "HorizontalAdpater_싸피"

class PlaceHorizontalAdapter(private val items: List<Place>) :
    RecyclerView.Adapter<PlaceHorizontalAdapter.HorizontalViewHolder>() {

    lateinit var placeHorizontalListener: ItemClickListener

    interface ItemClickListener {
        fun onClick(placeId: Int)
    }

    inner class HorizontalViewHolder(private val binding: ListPlaceItemHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bindInfo(place: Place) {

            binding.apply {
                title.text = place.placeName
                image.setImageResource(itemView.context.resources.getIdentifier(place.placeImg, "drawable", itemView.context.packageName))

                Glide.with(itemImage.context)
                    .load("${SERVER_URL}images/${place.placeImg}")
                    .into(itemImage)


                initSchedule(this@HorizontalViewHolder)

                drawSchedule(this@HorizontalViewHolder, "10:30", "11:30")
                drawSchedule(this@HorizontalViewHolder, "15:30", "17:00")

                eraseBeforeCurrentTime(this@HorizontalViewHolder)
                root.setOnClickListener {
                    placeHorizontalListener.onClick(place.placeId)
                }
            }
        }

        val image: ImageView = itemView.findViewById(R.id.itemImage)
        val title: TextView = itemView.findViewById(R.id.itemTitle)
        val firstRowTimeLabels: LinearLayout = itemView.findViewById(R.id.firstRowTimeLabels)
        val firstRowBlocks: LinearLayout = itemView.findViewById(R.id.firstRowBlocks)
        val secondRowTimeLabels: LinearLayout = itemView.findViewById(R.id.secondRowTimeLabels)
        val secondRowBlocks: LinearLayout = itemView.findViewById(R.id.secondRowBlocks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val binding = ListPlaceItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bindInfo(items[position])
//        holder.title.text = item.placeName
//        holder.image.setImageResource(holder.itemView.context.resources.getIdentifier(item.placeImg, "drawable", holder.itemView.context.packageName))

//        initSchedule(holder)
//
//        drawSchedule(holder, "10:30", "11:30")
//        drawSchedule(holder, "15:30", "17:00")
//
//        eraseBeforeCurrentTime(holder)
    }

    override fun getItemCount(): Int = items.size

    @RequiresApi(Build.VERSION_CODES.O)
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
                    setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.neon_main))
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
                    setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.neon_main))
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
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun drawSchedule(
        holder: HorizontalViewHolder,
        startTime: String,
        endTime: String
    ) {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val start = LocalTime.parse(startTime, formatter)
        val end = LocalTime.parse(endTime, formatter)
//        Log.d(TAG, "drawSchedule: $end")

        for (item in holder.firstRowBlocks) {
//            Log.d(TAG, "drawSchedule: ${item.id}")
            val itemTime = LocalTime.of(item.id / 100, item.id % 100) // id를 시간으로 변환 (예: 1230 -> 12:30)
            if (!itemTime.isBefore(start) && !itemTime.isAfter(end) && itemTime != end) {
                item.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grey_main))
            }
        }

        for (item in holder.secondRowBlocks) {
            if(item.id < 0){
                break
            }
//            Log.d(TAG, "drawSchedule: ${item.id}")
            val itemTime = LocalTime.of(item.id / 100, item.id % 100)
            if (!itemTime.isBefore(start) && !itemTime.isAfter(end) && itemTime != end) {
                item.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grey_main))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eraseBeforeCurrentTime(holder: HorizontalViewHolder){
        val now = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm") // 원하는 시간 형식 설정
        val endTime = now.format(formatter)
        val end = LocalTime.parse(endTime, formatter)
//        Log.d(TAG, "eraseBeforeCurrentTime: $end")

        for (item in holder.firstRowBlocks) {
//            Log.d(TAG, "eraseBeforeCurrentTime: ${item.id}")
            val itemTime = LocalTime.of(item.id / 100, item.id % 100) // id를 시간으로 변환 (예: 1230 -> 12:30)
            if (itemTime.isBefore(end)) {
                var color = (item.background as ColorDrawable).color
                val newColor = ColorUtils.setAlphaComponent(color, (0.2 * 255).toInt())
                item.setBackgroundColor(newColor)
            }
            else{
                return
            }
        }

        for (item in holder.secondRowBlocks) {
            if(item.id < 0){
                break
            }
//            Log.d(TAG, "eraseBeforeCurrentTime: ${item.id}")
            val itemTime = LocalTime.of(item.id / 100, item.id % 100)
            if (itemTime.isBefore(end)) {
                item.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.darkgrey_main))
            }
            else{
                return
            }
        }
    }
}
