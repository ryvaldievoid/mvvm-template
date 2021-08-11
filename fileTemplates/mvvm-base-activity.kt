package ${packageName};

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ${applicationPackage}.R
import ${applicationPackage}.BaseActivity
import ${applicationPackage}.util.obtainViewModel
import ${packageName}.${className}ViewModel


class ${className}Activity : BaseActivity<${className}Binding>() {
    
    lateinit var mViewModel: HomeViewModel
    
    override fun bindLayoutRes(): Int {
        return R.layout.${layoutName}
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindToolbarId(): Int? {
        TODO("Not yet implemented")
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindFragmentContainerId(): Int? {
        TODO("Not yet implemented")
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindFragmentInstance(): Fragment? {
        TODO("Not yet implemented")
    }

    override fun onStartWork() {
        TODO("Not yet implemented")
    }

    override fun setupViewModel() {
        mViewModel = obtainViewModel(${className}ViewModel::class.java).apply {
            start()
        }
        viewBinding.mViewModel = mViewModel
    }
    
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ${className}Activity::class.java))
        }
    }
}