package com.overnightstay.view.night_bus.minigame

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.R
import com.overnightstay.databinding.FragmentGameToFeedTheNeedyBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameToFeedTheNeedyFragment : Fragment() {

    private var count = 0

    private lateinit var person1: Person
    private lateinit var person2: Person
    private lateinit var person3: Person

    private var _binding: FragmentGameToFeedTheNeedyBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var array = mutableListOf(
        "В Ночлежке мы следим, чтобы наши сотрудники и волонтеры чувствовали себя\nкомфортно, отдыхали.\nЯ тоже предлагаю тебе отдохнуть. Давай сыграем в мини игру.",
        "Нужно накормить нуждающихся. Будь внимателен. На подносе должны быть все три\nсоставляющих ужина. Нужно поторопиться, у клиентов есть шкала ожидания.\nЖелаю удачи!"
    )

    private lateinit var viewModel: GameNightBusViewModel

    @Inject
    lateinit var vmFactory: GameNightBusViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameToFeedTheNeedyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[GameNightBusViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countNegativ.collect {
                if (it == 0) return@collect
                Snackbar.make(
                    binding.root,
                    "Довольных = ${viewModel.countPositiv.value}, Недовольных = $it",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countPositiv.collect {
                if (it == 0) return@collect
                Snackbar.make(
                    binding.root,
                    "Довольных = $it, Недовольных = ${viewModel.countNegativ.value}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isGameOver.collect {
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "Конец игры. Довольных = ${viewModel.countPositiv.value}, Недовольных = ${viewModel.countNegativ.value}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        initNeedies()

        binding.text.animateCharacterByCharacter2(text = array[0], animator = currentAnimator)
        binding.dialogNext.isClickable = true

        binding.dialogNext.setOnClickListener {
            if (currentAnimator.isRunning) {
                currentAnimator.end()
                return@setOnClickListener
            }

            count++

            when (count) {
                1 -> {
                    binding.main.setBackgroundResource(R.drawable.bg_game_to_feed_the_needy)
                    binding.text.animateCharacterByCharacter2(
                        text = array[count],
                        animator = currentAnimator
                    )
                    lifecycleScope.launch {
                        delay(25L * array[count].length.toLong())
                    }
                }

                2 -> {
                    with(binding) {
                        theNeedy1.setImageResource(person2.needy.idImg)
                        person2.startTimer()
                        theNeedy2.setImageResource(person1.needy.idImg)
                        person1.startTimer()
                        theNeedy3.setImageResource(person3.needy.idImg)
                        person3.startTimer()

                        catStatus.visibility = View.GONE
                        rectangle.visibility = View.GONE
                        statusName.visibility = View.GONE
                        text.visibility = View.GONE
                        dialogNext.visibility = View.GONE
                        needy.visibility = View.VISIBLE
                        teapod.visibility = View.VISIBLE
                        cup1.visibility = View.VISIBLE
                        cup2.visibility = View.VISIBLE
                        cup3.visibility = View.VISIBLE
                        meal1.visibility = View.VISIBLE
                        meal2.visibility = View.VISIBLE
                        meal3.visibility = View.VISIBLE
                        bread1.visibility = View.VISIBLE
                        bread2.visibility = View.VISIBLE
                        bread3.visibility = View.VISIBLE
                        map.visibility = View.VISIBLE
                    }
                }
            }
            binding.map.setOnClickListener {
                findNavController().navigate(R.id.action_gameToFeedTheNeedyFragment_to_locationMapFragment)
            }
        }

        binding.rules.setOnClickListener {
            findNavController().navigate(R.id.action_gameToFeedTheNeedyFragment_to_contentsOfBookFragment)
        }

        initBtnListeners()
    }

    private fun initNeedies() {
        person1 = Person(
            pos = PlacesPersons.CENTER,
            progressStatus = binding.pbStress1,
            needy = Needies.entries.toTypedArray().random()
        ) { viewModel.timerFinish() }
        person2 = Person(
            pos = PlacesPersons.LEFT,
            progressStatus = binding.pbStress2,
            needy = Needies.entries.toTypedArray().filter { it != person1.needy }.random()
//            needy = Needies.entries.toTypedArray().random()
        ) { viewModel.timerFinish() }
        person3 = Person(
            pos = PlacesPersons.RIGHT,
            progressStatus = binding.pbStress3,
            needy = Needies.entries.toTypedArray()
                .filter { it != person1.needy && it != person2.needy }.random()
        ) { viewModel.timerFinish() }
    }

    private fun initBtnListeners() = with(binding) {
        listOf(cup1, cup2, cup3).forEach { cup -> cup.setOnClickListener { add_cup(it) } }
        listOf(meal1, meal2, meal3).forEach { meal -> meal.setOnClickListener { add_meal(it) } }
        listOf(
            bread1,
            bread2,
            bread3
        ).forEach { bread -> bread.setOnClickListener { add_bread(it) } }
        listOf(cup, meal, bread).forEach { it.setOnClickListener { checkTray() } }
    }

    private fun checkTray() {
        if (!binding.cup.isGone && !binding.meal.isGone && !binding.bread.isGone) {
            binding.cup.isGone = true
            binding.meal.isGone = true
            binding.bread.isGone = true

            binding.cup1.isGone = false
            binding.cup2.isGone = false
            binding.cup3.isGone = false
            binding.meal1.isGone = false
            binding.meal2.isGone = false
            binding.meal3.isGone = false
            binding.bread1.isGone = false
            binding.bread2.isGone = false
            binding.bread3.isGone = false

            eat_needy()
            binding.theNeedy1.setImageResource(person2.needy.idImg)
            binding.theNeedy2.setImageResource(person1.needy.idImg)
            binding.theNeedy3.setImageResource(person3.needy.idImg)
        }
    }

    private fun add_cup(view: View) {
        if (binding.cup.isGone) {
            view.isGone = true
            binding.cup.isGone = false
        }
    }

    private fun add_meal(view: View) {
        if (binding.meal.isGone) {
            view.isGone = true
            binding.meal.isGone = false
        }
    }

    private fun add_bread(view: View) {
        if (binding.bread.isGone) {
            view.isGone = true
            binding.bread.isGone = false
        }
    }

    private fun eat_needy() {

        if (!person1.getAngry()) {
            viewModel.addPositiv()
        }
        person1.stopTimer()
        person1 = person2
        person1.pos = PlacesPersons.CENTER
        person1.progressStatus = binding.pbStress2
        person2 = person3
        person2.pos = PlacesPersons.LEFT
        person2.progressStatus = binding.pbStress1
        person3 = Person(
            pos = PlacesPersons.RIGHT,
            progressStatus = binding.pbStress3,
            needy = Needies.entries.toTypedArray()
                .filter { it != person1.needy && it != person2.needy }.random()
        ) { viewModel.timerFinish() }
        person3.startTimer()

    }

    enum class PlacesPersons {
        CENTER,
        LEFT,
        RIGHT,
    }

    enum class Needies(val idImg: Int) {
        NEEDY1(R.drawable.img_the_needy_1),
        NEEDY2(R.drawable.img_the_needy_2),
        NEEDY3(R.drawable.img_the_needy_3),
        NEEDY4(R.drawable.img_the_needy_4),
        NEEDY5(R.drawable.img_the_needy_5),
        NEEDY6(R.drawable.img_the_needy_6);
    }

    class Person(
        var pos: PlacesPersons,
        var progressStatus: ProgressBar,
        val needy: Needies,
        private val onTimerFinish: () -> Unit
    ) {
        private var timer: CountDownTimer? = null
        private var isAngry: Boolean = false

        init {
            progressStatus.max = TIMER_STRESS_BAR.toInt()

            timer = object : CountDownTimer(TIMER_STRESS_BAR, INTERVAL_TIMER_STRESS_BAR) {
                override fun onTick(millisUntilFinished: Long) {
                    setProgress(millisUntilFinished)
                }

                override fun onFinish() {
                    setAngry()
                    onTimerFinish()
                }

            }
        }

        fun stopTimer() {
            timer?.cancel()
        }

        fun startTimer() {
            timer?.start()
        }

        fun setProgress(milis: Long) {
            progressStatus.progress = milis.toInt()
        }

        fun setAngry() {
            isAngry = true
        }

        fun getAngry(): Boolean {
            return isAngry
        }
    }

    companion object {
        private const val TIMER_STRESS_BAR = 25000L
        private const val INTERVAL_TIMER_STRESS_BAR = 100L
        private const val MAX_NEEDIES = 10

    }
}