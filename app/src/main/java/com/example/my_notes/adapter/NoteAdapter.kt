package com.example.my_notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.my_notes.databinding.NoteItemBinding
import com.example.my_notes.fragments.HomeFragmentDirections
import com.example.my_notes.model.NoteData

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    class NoteViewHolder(val itemBinding: NoteItemBinding):RecyclerView.ViewHolder(itemBinding.root)



        // güncelleme durumlarında çalışır
    private val differCallback= object :DiffUtil.ItemCallback<NoteData>(){

        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
          return oldItem.id == newItem.id
                  &&
                  oldItem.noteDesc == newItem.noteDesc
                  &&
                  oldItem.noteTitle == newItem.noteTitle
        }
        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)









    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun getItemCount(): Int {
      return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        // Belirli pozisyondaki notu al
        val currentNote = differ.currentList[position]

        // ViewHolder'ın içindeki görünümlere notun başlık ve açıklama bilgilerini yerleştir
        holder.itemBinding.noteTitle.text=currentNote.noteTitle
        holder.itemBinding.noteDesc.text=currentNote.noteDesc

        // note a tıklaynca edit note sayfasına yönlendiriyor
        // note objesini intent ile başka sayfaya yönlendiriyo gibi düşün.
        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }







}