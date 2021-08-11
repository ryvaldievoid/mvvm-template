package ${packageName}


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ${packageName}.base.BaseFragment
import ${packageName}.databinding.${fragmentLayoutName}Binding
import ${packageName}.${className}ViewModel


class ${className}Fragment : BaseFragment(${className}ViewModel) {    

	lateinit var mViewDataBinding: ${fragmentLayoutName}Binding
	lateinit var mViewModel: ${className}ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

          	mViewDataBinding = ${fragmentLayoutName}Binding.inflate(inflater, container, false)
          	mViewModel = (activity as ${className}Activity).obtainViewModel().apply {
          	    start()
          	}

          	mViewDataBinding.mViewModel = mViewModel.apply{

          	}
          	                    
//          	mViewDataBinding.mListener = object : ${className}UserActionListener{
//            
//        	}

          	return mViewDataBinding.root        
    }

    override fun onCreateObserver(viewModel: ${className}ViewModel) {
        TODO("Not yet implemented")
    }

    override fun setContentData() {
        mViewDataBinding.title.text = "Tes Fragment"
    }

    override fun setMessageType(): String {
        return MESSAGE_TYPE_SNACK
    }

    override fun onDestroyObserver(viewModel: ${className}ViewModel) {
        TODO("Not yet implemented")
    }

    companion object {
    
        fun newInstance() = ${className}Fragment().apply {

        }

    }

}
