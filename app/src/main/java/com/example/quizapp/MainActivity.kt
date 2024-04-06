package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.postDelayed
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val questions = arrayOf("What does the abbreviation \"LAN\" stand for?",
        "Which component does the abbreviation \"CPU\" identify?",
        "In which subject area is \"HTML\" a markup language used?",
        "What does the abbreviation \"IP\" stand for?",
            "Which technology does the abbreviation \"VPN\" describe?")
    private val options = arrayOf(
        arrayOf("A) Local Area Network\n",
            "B) Long Access Node\n",
            "C) Large Area Network"),
        arrayOf("A) Central Processing Unit\n",
                "B) Computer Power Unit\n",
                "C) Control Panel Unit"),
        arrayOf("A) Creating web pages\n",
                "B) Video editing\n",
                "C) Database management"),
        arrayOf("A) Internet Protocol\n",
                "B) Information Provider\n",
                "C) Internal Process"),
        arrayOf("A) Virtual Private Network\n",
                "B) Voice Processing Node\n",
                "C) Video Production Network"))
    private val correctOptions = arrayOf(0,0,0,0,0)
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayQuestions()
        binding.option1Button.setOnClickListener{checkAnswer(0)}
        binding.option2Button.setOnClickListener{checkAnswer(1)}
        binding.option3Button.setOnClickListener{checkAnswer(2)}
        binding.restartButton.setOnClickListener { restartQuiz() }
    }
    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }
    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
    }
    private fun showResults(){
        Toast.makeText(this,"Your Score: $score out of ${questions.size}",Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }
    private fun displayQuestions(){
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }
    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctOptions[currentQuestionIndex]
        if(selectedAnswerIndex==correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        }else {
            wrongButtonColors(selectedAnswerIndex)
        }
        if(currentQuestionIndex<(questions.size-1)){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestions()},1000)
        }else {
            showResults()
        }
    }
    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestions()
        binding.restartButton.isEnabled = false
    }
}