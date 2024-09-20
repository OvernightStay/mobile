package com.overnightstay.view.restore

import androidx.lifecycle.ViewModel
import com.overnightstay.domain.usecases.ConfirmCodeUseCase
import com.overnightstay.domain.usecases.GetCodeUseCase
import com.overnightstay.domain.usecases.RePassUseCase

class RestoreViewModel(
    private val getCodeUseCase: GetCodeUseCase,
    private val confirmCodeUseCase: ConfirmCodeUseCase,
    private val rePassUseCase: RePassUseCase,
) : ViewModel() {
}