package com.company_name.utils

//class ViewModelFactory private constructor(
//        private val mApplication: Application,
//        private val enutriRepository: EnutriRepository
//) : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>) =
//            with(modelClass) {
//                when {
//                    isAssignableFrom(ElearningViewModel::class.java) ->
//                        ElearningViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(ModuleDetailViewModel::class.java) ->
//                        ModuleDetailViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(LessonViewModel::class.java) ->
//                        LessonViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(OldQuizViewModel::class.java) ->
//                        OldQuizViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(CertificateViewModel::class.java) ->
//                        CertificateViewModel(mApplication, enutriRepository)
//
//                    /**
//                     * V2
//                     * */
//                    isAssignableFrom(QuizViewModel::class.java) ->
//                        QuizViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(PustakaViewModel::class.java) ->
//                        PustakaViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(HistoryViewModel::class.java) ->
//                        HistoryViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(HomeViewModel::class.java) ->
//                        HomeViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(ForumViewModel::class.java) ->
//                        ForumViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(ForumDetailViewModel::class.java) ->
//                        ForumDetailViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(SeeAllViewModel::class.java) ->
//                        SeeAllViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(LoginViewModel::class.java) ->
//                        LoginViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(RegisterViewModel::class.java) ->
//                        RegisterViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(PustakaDetailViewModel::class.java) ->
//                        PustakaDetailViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(PustakaSeeAllViewModel::class.java) ->
//                        PustakaSeeAllViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(ChildSelectionViewModel::class.java) ->
//                        ChildSelectionViewModel(mApplication, enutriRepository)
//                    isAssignableFrom(ToolsViewModel::class.java) ->
//                        ToolsViewModel(mApplication, enutriRepository)
//
//                    else ->
//                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
//                }
//            } as T
//
//    companion object {
//
//        @SuppressLint("StaticFieldLeak")
//        @Volatile
//        private var INSTANCE: ViewModelFactory? = null
//
//        @JvmStatic
//        fun getInstance(mApplication: Application) =
//                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
//                    INSTANCE ?: ViewModelFactory(mApplication,
//                            Injection.provideEnutriRepository(mApplication.applicationContext))
//                            .also { INSTANCE = it }
//                }
//    }
//}