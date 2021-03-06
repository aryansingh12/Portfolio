package com.himanshurawat.portfolio.ui.education

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.himanshurawat.portfolio.R
import com.himanshurawat.portfolio.adapter.ProjectsFragmentAdapter
import com.himanshurawat.portfolio.pojo.Projects
import com.himanshurawat.portfolio.ui.main.PortfolioMakerEventListener

class ProjectsFragment: Fragment(), EducationFragmentContract.View, View.OnClickListener {
    override fun onClick(v: View?) {

        if(v != null){
            when(v.id){

                backButton.id ->{
                    listener.backButtonClicked("ProjectsFragment")
                }

                nextButton.id ->{
                    listener.nextQuestionButtonClicked("ProjectsFragment")
                }
            }
        }

    }

    private lateinit var projectsList: MutableList<Projects>
    private lateinit var projectsRecyclerView: RecyclerView
    private lateinit var projectsAdapter: ProjectsFragmentAdapter

    private lateinit var addProject: MaterialButton
    private lateinit var backButton: MaterialButton
    private lateinit var nextButton: MaterialButton

    private lateinit var listener: PortfolioMakerEventListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            this.listener = context as PortfolioMakerEventListener
        }catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement PortfolioMakerEventListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_projects,container,false)

        setup(view)
        addProject.setOnClickListener{
            addProject()
        }
        initSwipe()
        return view
    }

    private fun setup(view: View){
        //TextInputEditText
        addProject = view.findViewById(R.id.fragment_projects_add_material_button)
        backButton = view.findViewById(R.id.fragment_projects_go_back_material_button)
        nextButton = view.findViewById(R.id.fragment_projects_next_question_material_button)

        projectsList = arrayListOf()
        projectsList.add(Projects())

        projectsAdapter = ProjectsFragmentAdapter(context!!,projectsList)
        projectsRecyclerView = view.findViewById(R.id.fragment_projects_recycler_view)
        projectsRecyclerView.adapter = projectsAdapter
        projectsRecyclerView.layoutManager = LinearLayoutManager(context!!,RecyclerView.VERTICAL,false)

        nextButton.setOnClickListener(this)
        backButton.setOnClickListener(this)
    }

    private fun addProject() {
        projectsList.add(Projects())
        projectsAdapter.notifyDataSetChanged()
    }

    private fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    projectsList.removeAt(position)
                    projectsAdapter.notifyDataSetChanged()
                }
            }

        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(projectsRecyclerView)
    }
}
