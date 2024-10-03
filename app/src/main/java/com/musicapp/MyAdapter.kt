package com.musicapp

import android.content.Context
import android.media.Image
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.currentCoroutineContext


class MyAdapter (val context: Context, val dataList: List<Data>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {


        val image:ImageView = itemView.findViewById(R.id.ImageOfPic)
        val musicTitle:TextView = itemView.findViewById(R.id.TitleOfSong)
        val subTitle:TextView = itemView.findViewById(R.id.SubtitleOfSong)

        val play: ImageButton = itemView.findViewById(R.id.PlayBtn)
        val Pause:ImageButton = itemView.findViewById(R.id.PauseBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        create the view in case of layout manager fail to create the Virwew for the data

        val itemView = LayoutInflater.from(context).inflate(R.layout.eachview,parent,false)
        return MyViewHolder(itemView)


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val CurrentData = dataList[position]

        holder.musicTitle.text = CurrentData.title
        holder.subTitle.text = CurrentData.type

        // iamge of the song
        Picasso.get().load(CurrentData.album.cover).into(holder.image)
        // media player
        val mediaPlayer = MediaPlayer.create(context,CurrentData.preview.toUri())
//        populate the data into View

        holder.play.setOnClickListener(){
            print("Music Play")
            mediaPlayer.start()

        }
        holder.Pause.setOnClickListener(){
            mediaPlayer.stop()

        }
    }

}