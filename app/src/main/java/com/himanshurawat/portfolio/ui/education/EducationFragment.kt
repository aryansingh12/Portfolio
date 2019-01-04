package com.himanshurawat.portfolio.ui.education

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.himanshurawat.portfolio.R
import com.himanshurawat.portfolio.adapter.EducationFragmentAdapter
import com.himanshurawat.portfolio.adapterjava.Educ
import com.himanshurawat.portfolio.pojo.Education
import com.himanshurawat.portfolio.ui.main.PortfolioMakerEventListener
import kotlinx.android.synthetic.main.fragment_education.*

class EducationFragment: Fragment(), EducationFragmentContract.View {

    private lateinit var educationList: MutableList<Education>
    private lateinit var educationRecyclerView: RecyclerView
    private lateinit var educationAdapter: EducationFragmentAdapter

    private lateinit var addEduc: TextInputEditText
    private lateinit var backButton: MaterialButton
    private lateinit var nextQuestionButton: MaterialButton

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
        val view = inflater.inflate(R.layout.fragment_education,container,false)

        setup(view)

        nextQuestionButton.setOnClickListener {

//            resetTextInputLayoutErrorMessages()

//            val firstName = firstNameTextInputEditText.text.toString()
//            val email = emailTextInputEditText.text.toString()
//            val mobileNumber = mobileNumberTextInputEditText.text.toString()

//            if(presenter.validate(firstName,email,mobileNumber)){
                listener.nextQuestionButtonClicked("EducationFragment")
//            }

            addEduc.setOnClickListener{
                var educ = Education("","","","","")
                educationList.add(educ)
                educationAdapter.notifyDataSetChanged()
            }


        }

        return view
    }

    private fun setup(view: View){
        //TextInputEditText
        //addEduc = view.findViewById(R.id.addEduc)
        backButton = view.findViewById(R.id.backButton)
        nextQuestionButton = view.findViewById(R.id.nextButton)
        educationList = arrayListOf()
        educationList.add(Education())
        educationList.add(Education())
        educationAdapter = EducationFragmentAdapter(context!!,educationList)
        educationRecyclerView = view.findViewById(R.id.fragment_education_recycler_view)
        educationRecyclerView.adapter = educationAdapter
        educationRecyclerView.layoutManager = LinearLayoutManager(context!!,RecyclerView.VERTICAL,false)

    }

    fun addEduc() {
    // add the list rv here
//        val educ: Educ("",5,"","")
//        education.add(educ)
//        educ_rv.layoutManager = LinearLayoutManager(this)
//        educ_rv.adapter = EducationAdapter(education, this)

    }
}