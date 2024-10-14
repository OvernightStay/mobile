package com.overnightstay.view.location_map

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentLocationMapBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LocationMapFragment : Fragment() {

    private var _binding: FragmentLocationMapBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var array = mutableListOf(
        "У тебя появился Домик Опыта\nПри успешном прохождении локаций, у тебя будет накапливаться опыт. В дальнейшем,\nты можешь зайти в свой дом и расставить там предметы из рюкзака.",
        "Карта поможет тебе вернуться на главный экран и выбрать следующую локацию.\nЖми на меня, если нужна будет помощь.",
        "Вперед! К новым испытаниям и приключениям! \nНу, а я буду всегда с тобой! Муррр–р–р–р\nВыбери куда отправимся дальше"
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
        _binding = FragmentLocationMapBinding.inflate(inflater, container, false)
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
                    1 -> binding.catAvatar.visibility = View.VISIBLE
                }
                binding.text.animateCharacterByCharacter2(
                    text = array[count],
                    animator = currentAnimator
                )

                lifecycleScope.launch {
                    delay(25L * array[count].length.toLong())
                }

            } else if (count == 3) {
                binding.rectangle.visibility = View.INVISIBLE
                binding.catStatus.visibility = View.INVISIBLE
                binding.statusName.visibility = View.INVISIBLE
                binding.text.visibility = View.INVISIBLE
                binding.dialogNext.visibility = View.INVISIBLE
            }
        }

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        rules.setOnClickListener {
            findNavController().navigate(R.id.action_locationMapFragment_to_contentsOfBookFragment)
        }

        // Устанавливаем общий обработчик клика на все ImageView элементы
        val onClickListener = View.OnClickListener { view ->
            if (view.alpha == 0f) {
                view.alpha = 1f
                lifecycleScope.launch {
                    delay(2000L)

                    // Ищем в enum элемент по id
                    val enumValue = ImageViewElements.entries.find { it.imageViewId == view.id }
                    if(enumValue?.actionId != null) {
                        enumValue.onClickImage {
                            findNavController().navigate(enumValue.actionId)
                        }
                    }
                }
            } else {
                view.alpha = 0f
            }
        }

        nightBus.setOnClickListener(onClickListener)
        imgHouseShower.setOnClickListener(onClickListener)
        imgHouseLaundry.setOnClickListener(onClickListener)
        imgHouseNursing.setOnClickListener(onClickListener)
        imgHouseRehabilitation.setOnClickListener(onClickListener)
        imgHouseConsultation.setOnClickListener(onClickListener)
        imgHouseHalfpath.setOnClickListener(onClickListener)
        imgHouseWarm.setOnClickListener(onClickListener)
        imgHouseOfDistribution.setOnClickListener(onClickListener)
        imgHouseNight.setOnClickListener(onClickListener)
        imgHousePsychologist.setOnClickListener(onClickListener)
    }

    enum class ImageViewElements(val imageViewId: Int, val actionId: Int? = null) {
        NIGHT_BUS(R.id.night_bus, R.id.action_locationMapFragment_to_nightBusFragment),
        IMG_HOUSE_SHOWER(R.id.img_house_shower, R.id.action_locationMapFragment_to_dialogOnTheStreetFragment),
        IMG_HOUSE_LAUNDRY(R.id.img_house_laundry),
        IMG_HOUSE_NURSING(R.id.img_house_nursing),
        IMG_HOUSE_REHABILITATION(R.id.img_house_rehabilitation),
        IMG_HOUSE_CONSULTATION(R.id.img_house_consultation),
        IMG_HOUSE_HALFPATH(R.id.img_house_halfpath),
        IMG_HOUSE_WARM(R.id.img_house_warm),
        IMG_HOUSE_OF_DISTRIBUTION(R.id.img_house_of_distribution),
        IMG_HOUSE_NIGHT(R.id.img_house_night),
        IMG_HOUSE_PSYCHOLOGIST(R.id.img_house_psychologist);

        fun onClickImage(onClick: () -> Unit) {
            onClick()
        }
    }
}