package com.example.notes

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.DataService

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var list = DataService.getAllNotes("")

    class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardView = view.findViewById<CardView>(R.id.note_card)
        val textView = view.findViewById<TextView>(R.id.note_list_text)
        val description = view.findViewById<TextView>(R.id.note_description)
        val arrow = view.findViewById<ImageView>(R.id.arrow_button)
        val hiddenView = view.findViewById<ConstraintLayout>(R.id.hidden_view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_list_view, parent, false)
        return NoteViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = list.get(position)
        holder.textView.text = item.name
        holder.description.text = item.description

        holder.cardView.setOnClickListener {
            // If view is currently visible, make invisible
            if (holder.hiddenView.visibility == View.VISIBLE) {
                holder.hiddenView.visibility = View.GONE
                holder.arrow.setImageResource(R.drawable.ic_expand_more)
            } else {
                // View currently hidden, so make visible
                holder.hiddenView.visibility = View.VISIBLE
                holder.arrow.setImageResource(R.drawable.ic_expand_less)
            }
        }

        // TODO
//        holder.button.setOnClickListener {
//            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(letter = holder.button.text.toString())
//            holder.view.findNavController().navigate(action)
//        }
    }

    public fun onSearchChange(value: String) {
        list = DataService.getAllNotes(value)
        notifyDataSetChanged()
    }



}