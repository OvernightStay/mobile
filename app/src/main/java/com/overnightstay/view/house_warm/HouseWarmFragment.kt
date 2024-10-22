package com.overnightstay.view.house_warm

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.R
import com.overnightstay.databinding.FragmentHouseWarmBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import com.overnightstay.view.domain.ScreenSaver
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class HouseWarmFragment : Fragment() {

    var stress = Stress.GREEN

    private var _binding: FragmentHouseWarmBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var answer = mutableListOf(
        mutableListOf(16, 2, 0),
        mutableListOf(19, 2, 0),
        mutableListOf(23, 2, 0),
        mutableListOf(25, 1, 0),
        mutableListOf(27, 2, 0),
    )

    private var array = mutableListOf(
        "Рад тебя видеть! Мы еще на шаг ближе к помощи нашим подопечным! Мы в Пункте Обогрева",
        "Интересно, как здесь все устроено?",
        "Палатка или Пункт Обогрева еще называем Точкой Входа в Ночлежку. Цель проекта: \n • сохранение жизни и здоровья наших гостей. \n • мы не только предлагаем переночевать, но и рассказываем о других проектах и услугах.",
        "Это так здорово! А как гости находят локацию? ",
        "После установки Палатки, мы устанавливаем навигационные таблички в этом районе, а также раздаем листовки с информацией о пункте. Их также можно распечатать желающим на нашем сайте. На улице становится прохладно, пойдем покажу что внутри.",
        "Здесь гораздо теплее! Расскажи пожалуйста, как в таких спартанских условиях создается работа?",
        "Благодаря Координаторам и Дежурным. Координатор отвечает:\n • чтобы вовремя была вода, свет и тепло, \n • оплачивает счета, взаимодействуют с администрацией и другими организациями, \n • координирует Дежурных.",
        "О! А в чем заключается работа дежурных?\nКажется, что координатор справляется со всем!",
        "Это самое интересное! Дежурные - это те люди, которые проводят максимальное количество времени с нашими гостями! Дежурными могут быть наши бывшие подопечные или любые желающие. Они поддерживают дисциплину и сохраняют порядок",
        "Ага! Теперь понимаю, почему здесь такой порядок. Я вижу много предметов в Пункте обогрева! Расскажи, для чего они нужны?",
        "Дизельная пушка с дымоходом обеспечивает теплом всю палатку. Если пушка находится снаружи, то тепло приходит сразу в палатку через специальный рукав.",
        "Мы греем воду для гостей не в чайниках, а в бойлерах. Это помогает нам экономить электричество, а также дольше сохранять тепло питьевой воды.",
        "А где спят гости? Я не вижу здесь кроватей или раскладушек.",
        "Наши посетители спят в одежде, на туристических ковриках. Мы разрешаем проносить в палатку только самые необходимые вещи, в целях гигиены. После ухода гостей мы обрабатываем коврики дезинфицирующим средством.",
        "Это же так удобно! Коврики и тепло сохранят и компакты в размере. Тогда сколько же способна вместить гостей палатка?",
        "Сможешь примерно посчитать?","",
        "Так и есть! Палатка расчитана на 50 человек, но мы стараемся дать теплый ночлег всем, до последнего гостя.",
        "Как ты думаешь, для чего нам нужен этот прибор?","",
        "Абсолютно верно! С 8 вечера до 8 утра Дезар включён.",
        "Кажется, к нам кто-то зашел погреться! Это Матвей. Помоги расположиться нашему гостю.\nА мне пора! Удачи!",
        "Здравствуйте! Мне совсем некуда идти и я бы хотел у вас остаться пожить.","",
        "У меня нет документов, Вы меня пустите?","",
        "Нужно ли мне занимать очередь, чтобы точно иметь место на ночь?","",
        "Спасибо вам! Я пойду займу место.",
        "Пока меня не было, ты проделал(а) колоссальную работу! Так держать!",
        "В Ночлежке мы следим, чтобы наши сотрудники и волонтеры чувствовали себя комфортно, отдыхали.  Я тоже предлагаю тебе отдохнуть. Давай сыграем в игру.Помоги гостям найти Пункт обогрева. Проводи влево и вправо, и собирай человечков в палатку."
        )

    private val arrayRadioButtonText1 = mutableListOf(
        "","","","","","","","","","","","","","","","",
        "Думаю здесь будет удобно разместить не более 30 человек","","",
        "Это легко! Вероятно - это увлажнитель воздуха!","","","",
        "Конечно оставайтесь, пока не найдете место жительства и работу!","",
        "Пункт обогрева принимает без документов и прописки, в любом состоянии ","",
        "Желательно приходить заранее, т.к. мы не можем гарантировать свободное место "
    )

    private val arrayRadioButtonText2 = mutableListOf(
        "","","","","","","","","","","","","","","","",
        "Здесь прекрасно расположатся 50 гостей, а может и больше!","","",
        "Это Дезар! Им обеззараживают воздух на время прибывания посетителей","","","",
        "Вы можете ночевать неограниченное количество раз, но проживать можно с 20:00 до 08:00 с октября до апреля","",
        "К сожалению, не можем помочь. Для начала Вам необходимо восстановить документы ","",
        "В палатке Пункта обогрева Вам обязательно найдут теплое спальное место"
    )

    private var count = 0

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHouseWarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayImages = ScreenSaver.images
        val randomImage1 = arrayImages[Random.nextInt(arrayImages.size)]
        val randomImage2 = arrayImages[Random.nextInt(arrayImages.size)]
        binding.screenSaver.screen.setImageResource(randomImage1)
        binding.screenSaver.root.isGone = true

        binding.text.animateCharacterByCharacter2(text = array[0], animator = currentAnimator)
        binding.dialogNext.isClickable = true


        binding.dialogNext.setOnClickListener {
            if (currentAnimator.isRunning) {
                currentAnimator.end()
                return@setOnClickListener
            }

            if (!binding.radioGroup.isGone) {
                if (!binding.radioButton1.isChecked && !binding.radioButton2.isChecked) {
                    Snackbar.make(
                        binding.root,
                        "Выберите одну из опций.",
                        Snackbar.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                val index = answer.indexOfFirst { it[0] == count }
                if (index != -1) {
                    if (binding.radioButton1.isChecked) {
                        answer[index][2] = 1
                    } else {
                        answer[index][2] = 2
                    }
                    if (answer[index][1] != answer[index][2]) {

                        /*                        Snackbar.make(
                            binding.root,
                            "Ответ не правильный.",
                            Snackbar.LENGTH_LONG
                        ).show()*/

                        stress.getNextStress()?.let {
                            binding.stress.setImageResource(it.idImg)
                            stress = it
                        }
                    }
                }
                println("answer $answer")
            }

            count++

            if (count < array.size) {
                binding.text.animateCharacterByCharacter2(text = array[count], animator = currentAnimator)

                when (count) {
                    1 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status)
                        user()
                    }
                    2 -> status()
                    3 -> user()
                    4 -> {
                        binding.bg.setImageResource(R.drawable.bg_house_warm_02)
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                        status()
                    }
                    5 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status)
                        binding.bg.visibility = View.GONE
                        binding.bg2.visibility = View.VISIBLE
                        user()
                    }
                    6 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                        binding.bg2.setImageResource(R.drawable.bg_house_warm_03)
                        status()
                    }
                    7 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status)
                        user()
                    }
                    8 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                        status()
                    }
                    9 -> {
                        binding.bg2.setImageResource(R.drawable.bg_house_warm_04)
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status)
                        user()
                    }
                    10 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                        status()
                        binding.item.visibility = View.VISIBLE
                    }
                    11 -> binding.item.setImageResource(R.drawable.img_house_warm_boiler)
                    12 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status)
                        binding.item.visibility = View.GONE
                        user()
                    }
                    13 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                        status()
                        binding.item.visibility = View.VISIBLE
                        binding.item.setImageResource(R.drawable.img_house_warm_travel_mats)
                    }
                    14 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status)
                        binding.item.visibility = View.GONE
                        user()
                    }
                    15 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                        status()
                    }
                    16, 19 -> {
                        user()
                        with(binding) {
                            catStatus.setBackgroundResource(R.drawable.img_cat_status)
                            text.visibility = View.GONE
                            radioGroup.clearCheck()
                            radioGroup.visibility = View.VISIBLE
                            radioButton1.text = arrayRadioButtonText1[count]
                            radioButton2.text = arrayRadioButtonText2[count]
                        }
                    }
                    17 -> {
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                        status()
                        binding.text.visibility = View.VISIBLE
                        binding.radioGroup.visibility = View.GONE
                    }
                    18 -> {
                        binding.item.visibility = View.VISIBLE
                        binding.item.setImageResource(R.drawable.img_house_warm_desar)
                    }
                    20 -> {
                        binding.radioGroup.visibility = View.GONE
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                        status()
                        binding.text.visibility = View.VISIBLE
                    }
                    21 -> {
                        lifecycleScope.launch {
                            binding.screenSaver.root.isGone = false
                            delay(4000)
                            binding.screenSaver.root.isGone = true
                        }
                        binding.item.visibility = View.GONE
                        binding.bg2.setImageResource(R.drawable.bg_house_warm_05)
                        binding.matwei.visibility = View.VISIBLE
                    }
                    22 -> {
                        binding.catStatus.visibility = View.GONE
                        binding.matwei.setImageResource(R.drawable.img_house_warm_matwei_sad)
                        status()
                        binding.statusName.text = "Матвей"
                    }
                    23, 25, 27 -> {
                        with(binding) {
                            text.visibility = View.GONE
                            matwei.setImageResource(R.drawable.img_house_warm_matwei)
                            radioGroup.clearCheck()
                            radioGroup.visibility = View.VISIBLE
                            radioButton1.text = arrayRadioButtonText1[count]
                            radioButton2.text = arrayRadioButtonText2[count]
                        }
                        user()
                    }
                    24, 26 -> {
                        status()
                        binding.matwei.setImageResource(R.drawable.img_house_warm_matwei_sad)
                        binding.text.visibility = View.VISIBLE
                        binding.radioGroup.visibility = View.GONE
                    }
                    28 -> {
                        status()
                        binding.matwei.setImageResource(R.drawable.img_house_warm_matwei_happy)
                        binding.text.visibility = View.VISIBLE
                        binding.radioGroup.visibility = View.GONE
                    }
                    29 -> {
                        with(binding) {
                            matwei.visibility = View.GONE
                            catStatus.visibility = View.VISIBLE
                            statusName.text = "Статус"
                        }
                    }
                    30 -> {
                        binding.user.visibility = View.GONE
                        binding.catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions_02)
                    }
                }
            } else if (stress == Stress.GREEN) {
                binding.screenSaver.screen.setImageResource(randomImage2)
                lifecycleScope.launch {
                    binding.screenSaver.root.isGone = false
                    delay(4000)
                    findNavController().navigate(R.id.action_houseWarmFragment_to_gameHouseWarmFragment)
                }

            } else {
                findNavController().navigate(R.id.action_houseWarmFragment_to_contentsOfBookFragment)
            }
        }
    }

    private fun status() {
        binding.statusName.visibility = View.VISIBLE
        binding.userName.visibility = View.INVISIBLE
        binding.rectangle.setBackgroundResource(R.drawable.dialog_house_shower_status)
    }
    private fun user() {
        binding.userName.visibility = View.VISIBLE
        binding.statusName.visibility = View.INVISIBLE
        binding.rectangle.setBackgroundResource(R.drawable.dialog_house_shower_user)
    }

    enum class Stress(val idImg: Int, val pos: Int) {
        GREEN(R.drawable.img_stress, 0),
        YELLOW(R.drawable.img_stress_yellow, 1),
        ORANGE(R.drawable.img_stress_orange, 2),
        RED(R.drawable.img_stress_red, 3);

        fun getNextStress(): Stress? {
            return if (pos < entries.size - 1) entries[pos + 1] else
                if (pos == 3) entries[pos] else null
        }
    }

}