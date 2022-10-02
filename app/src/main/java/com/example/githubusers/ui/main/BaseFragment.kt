package com.example.githubusers.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.githubusers.MainActivity
import com.example.githubusers.BR

abstract class BindingFragment<B : ViewDataBinding, M : ViewModel> constructor(@LayoutRes private val resourceId: Int) :
    Fragment() {

    abstract val viewModel: M

    lateinit var binding: B

    /**
     * MainActivity에서 사용하는 ViewModel 객체로 데이터 공유 또는 공통 처리를 위해 사용
     */
    val sharedViewModel: MainViewModel by activityViewModels()

    /**
     * 상속하는 Fragment 에서 activity에 접근할때 중복코드를 줄이기 위한 변수
     * lazy를 통해 호출시에만 초기화가 일어나도록 함
     */
    val main: MainActivity by lazy {
        requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding 객체 생성 상속받은 클래스에서 전달 받은 id값을 통해 layout를 생성
        binding = DataBindingUtil.inflate(inflater, resourceId, container, false)
        //binding 객체의 생명주기를 activity에서 관리 하도록 함
        binding.lifecycleOwner = activity

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.model, viewModel)
    }
}