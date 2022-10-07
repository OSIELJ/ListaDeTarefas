package com.osiel.listadetarefas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.osiel.listadetarefas.databinding.ResItemTaskBinding

class TaskAdapter(
    private val onClick : (Task) -> Unit,
    private val onDeleteClick : (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

    inner class TaskViewHolder(
        itemView: ResItemTaskBinding
    ) : RecyclerView.ViewHolder(itemView.root){

        private val tvTileTask : TextView
        private  val imgBtnDeleteTask : ImageButton
        private val clTask : ConstraintLayout

        init {
            tvTileTask = itemView.tvTitleTask
            imgBtnDeleteTask = itemView.imgBtnDeleteTask
            clTask = itemView.clTask
        }

        fun bind(task : Task, onDeleteClick: (Task) -> Unit, onClick: (Task) -> Unit){
            tvTileTask.text = task.title
            imgBtnDeleteTask.setOnClickListener {
                onDeleteClick(task)
            }
            clTask.setOnClickListener {
                onClick(task)
            }

            if (task.done){

                tvTileTask.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.white)
                )
                imgBtnDeleteTask.setImageResource(R.drawable.delete_white)
                clTask.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,R.color.success_green
                    )
                )

            }else {

                tvTileTask.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.black)
                )
                imgBtnDeleteTask.setImageResource(R.drawable.delete_black)
                clTask.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,R.color.bright_gray
                    )
                )

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            ResItemTaskBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(
            tasks[position],
            onDeleteClick,
            onClick
        )
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: Task){

        tasks.add(task)
        notifyItemInserted(tasks.size -1)

    }

    fun deleteTask(task: Task){

       val deletePosition = tasks.indexOf(task)
        tasks.remove(task)
        notifyItemRemoved(deletePosition)

    }

    fun updateTask(task: Task) {

        val updatePosition = tasks.indexOf(task)
        tasks[updatePosition] = task
        notifyItemChanged(updatePosition)

    }

    fun isEmpty() = tasks.isEmpty()

}