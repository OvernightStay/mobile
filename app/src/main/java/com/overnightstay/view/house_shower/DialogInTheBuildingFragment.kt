package com.overnightstay.view.house_shower

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentDialogInTheBuildingBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DialogInTheBuildingFragment : Fragment() {

    private var _binding: FragmentDialogInTheBuildingBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var array = mutableListOf(
        "Наши клиенты могут посетить Неравнодуш 1 раз в неделю. Но если нет большого количества людей, то клиенту не откажут. Воспользоваться услугами Неравнодуша необходимо  перед заселением в Реабилитационный приют Ночлежки и перед прохождением реабилитации в Городской наркологической больнице.",
        "Расскажи мне, пожалуйста, как тут все устроено!",
        "Здесь работает один сотрудник. В его обязанности входит:\n \u2022 заносить посетителей в CRM–систему, выдавать им туалетные принадлежности, халат,\n \u2022 следить за порядком и  соблюдением очереди. После душа клиенты могут выпить чай.",
        "Неравнодуш оборудован\n \u2022 душевыми кабинками, туалетами, стиральными и сушильными машинами.\n \u2022 oдин из блоков оборудован специально для маломобильных посетителей, им могут пользоваться люди в инвалидном кресле.",
        "Как здесь здорово все оборудовано!",
        "Неравнодуш работает 6 дней в неделю. Каждый вторник с 10:30 до 17:30 часов воспользоваться Неравнодушем смогут только женщины. Дело в том, что многие из наших клиенток пережили насилие и другой травмирующий опыт. Для них важно иметь возможность не пересекаться с мужчинами даже в общем холле.",
        "Важный момент – это уборка и дезинфекция помещения, халатов. Если в городе сложная эпидемиологическая обстановка дежурный на входе измеряет температуру у всех посетителей и выдает маски, перчатки и средство для дезинфекции всем, кто находится в зале ожидания.",
        "Все дверные ручки обрабатываются антисептиком каждый час, а душевые кабины и туалеты ‒ после каждого использования. После душа каждому клиенту, кто ожидает окончания стирки, выдают чистый обеззараженный халат.",
        "Это очень хорошо, что вы следите за чистотой в Неравнодуше!",
        "Кроме того, волонтёры-парикмахеры в 2023 году сделали 164 стрижки. Клиент может полностью привести себя в порядок перед собеседованием на работу.", "",
        "Также дежурный рассказывает клиентам про проекты Ночлежки и какую помощь они могут получить в наших проектах. У дежурного есть листовки с нужной информацией всех проектов. ",
        "Как интересно! Мне хотелось бы здесь работать. Вы очень хорошо здесь все обустроили! ",
        "Да! Мы постарались сделать всё необходимое, чтобы человек, который придет к нам за помощью, мог привести себя в порядок и сохранить достоинство."
    )

    private var count: Int = 0

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogInTheBuildingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.text.animateCharacterByCharacter2(text = array[0], animator = currentAnimator)
        binding.dialogNext.isClickable = true

        binding.dialogNext.setOnClickListener {
            if (currentAnimator.isRunning) {
                currentAnimator.end()
                return@setOnClickListener
            }

            count++

            if (count < array.size) {
                when (count) {
                    1 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_user)
                            statusName.visibility = View.GONE
                            userName.visibility = View.VISIBLE
                        }
                    }
                    2 -> {
                        with(binding) {
                            catStatus.setBackgroundResource(R.drawable.img_cat_status_emotions)
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_status)
                            statusName.visibility = View.VISIBLE
                            userName.visibility = View.GONE
                            bgHouseShower01.visibility = View.GONE
                            bgMichael.visibility = View.GONE
                            bgHouseShower02.visibility = View.VISIBLE
                            bgMichael02.visibility = View.VISIBLE
                            bgVladimir.visibility = View.VISIBLE
                        }
                    }
                    3 -> {
                        with(binding) {
                            catStatus.setBackgroundResource(R.drawable.img_cat_status)
                            bgHouseShower02.visibility = View.GONE
                            bgMichael02.visibility = View.GONE
                            bgVladimir.visibility = View.GONE
                            bgHouseShower01.visibility = View.VISIBLE
                            bgMichael.visibility = View.VISIBLE
                        }
                    }
                    4 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_user)
                            statusName.visibility = View.GONE
                            userName.visibility = View.VISIBLE
                        }
                    }
                    5 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_status)
                            statusName.visibility = View.VISIBLE
                            userName.visibility = View.GONE
                        }
                    }
                    8 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_user)
                            statusName.visibility = View.GONE
                            userName.visibility = View.VISIBLE
                        }
                    }
                    9 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_status)
                            statusName.visibility = View.VISIBLE
                            userName.visibility = View.GONE
                        }
                    }
                    10 -> {
                        binding.bgMichael.setImageResource(R.drawable.bg_house_shower_haircut)
                    }
                    11 -> {
                        with(binding) {
                            bgHouseShower01.visibility = View.GONE
                            bgMichael.visibility = View.GONE
                            bgHouseShower02.visibility = View.VISIBLE
                            bgMichael02.visibility = View.VISIBLE
                            bgVladimir.visibility = View.VISIBLE
                            leaflet.visibility = View.VISIBLE
                        }
                    }
                    12 -> {
                        with(binding) {
                            bgHouseShower02.visibility = View.GONE
                            bgMichael02.visibility = View.GONE
                            bgVladimir.visibility = View.GONE
                            leaflet.visibility = View.GONE
                            statusName.visibility = View.GONE
                            bgHouseShower01.visibility = View.VISIBLE
                            bgMichael.visibility = View.VISIBLE
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_user)
                            userName.visibility = View.VISIBLE
                        }
                    }
                    13 -> {
                        with(binding) {
                            bgHouseShower01.visibility = View.GONE
                            bgMichael.visibility = View.GONE
                            userName.visibility = View.GONE
                            bgHouseShower02.visibility = View.VISIBLE
                            bgMichael02.visibility = View.VISIBLE
                            bgVladimir.visibility = View.VISIBLE
                            leaflet.visibility = View.VISIBLE
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_status)
                            statusName.visibility = View.VISIBLE
                        }
                    }
                }

                binding.text.animateCharacterByCharacter2(
                    text = array[count],
                    animator = currentAnimator
                )
                lifecycleScope.launch {
                    delay(25L * array[count].length.toLong())
                }

            } else findNavController().navigate(R.id.action_dialogInTheBuildingFragment_to_dialogInTheBuilding2Fragment)
        }

        binding.rules.setOnClickListener {
            findNavController().navigate(R.id.action_dialogInTheBuildingFragment_to_contentsOfBookFragment)
        }
    }
}