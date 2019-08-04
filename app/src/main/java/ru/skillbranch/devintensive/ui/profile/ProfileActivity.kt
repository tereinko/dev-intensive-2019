package ru.skillbranch.devintensive.ui.profile

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.utils.Utils
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel

class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileViewModel
    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>
    /*
    * Вызывается при первом создании или перезапуске Activity
    *
    * Здесь задается внешний вид активности (UI) через метод setContentView().
    * инициализируются представления
    * представления связываются с необходимыми данными и ресурсами
    * связываются данными со списками
    *
    * Этот метод также предоставляет Bundle, содержащий ранее сохранённое
    * состояние Activity, если оно было.
    *
    * Всегда сопровождается вызовом onStart()
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews(savedInstanceState)
        initViewModel()
        Log.d("M_ProfileActivity", "onCreate")
    }

    /*
    * Если Activity возвращается в приоритетный режим после вызовы onStop(),
    * то в этом случае вызывается метод onRestart().
    * Т.е. вызывается после того, как Activity была остановлена и снова была запущена пользователем.
    * Всегда сопровождается вызовом метода onStart().
    *
    * используется для специальных действий, которые должны выполняться только при повторном запуске Activity
    * */
    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    /*
    * При вызове onStart() окно еще не видно пользователю, но вскоре будет видно.
    * Вызывается непосредственно перед тем, как активность становится видимой пользователю.
    *
    * Чтение из БД
    * Запуск сложной анимации
    * Запуск потоков, отслеживания показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
    * которые нужны исключительно для обновления пользовательского интерфейса
    *
    * Затем следует onResume(), если Activity выходит на передний план
    * */
    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    /*
    * Вызывается, когда Activity начнет взаимодействовать с пользователем.
    *
    * запуск воспроизведения анимации, аудио и видео
    * регистрация любых BroadcastReceiver или других процессов, которые вы освободили/приостановили в onPause()
    * выполнение любых других инициализаций, которые должны происходит после того, как Activity становится активной (камера).
    *
    * Тут должен быть максимально легкий и быстрый код, чтобы приложение оставалось отзывчивым
    * */
    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    /*
    * Метод onPause() вызывается после сворачивания текущей активности или перехода к новой
    * От onPause() можно перейти к вызову либо onResume(), либо onStop()/
    *
    * остановка анимации, аудио и видео
    * сохранение состояния пользовательского ввода (легкие процессы)
    * сохранение в DB, если данные должны быть доступны в новой Activity
    * остановка сервисов, подписок, BroadcastReceiver
    *
    * Тут должен быть максимально легкий и быстрый код, чтобы приложение оставалось отзывчивым
    * */
    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    /*
    * Метод onStop() вызывается когда Activity становится невидимой для пользователя.
    * Это может произойти при её уничтожении или, если была запущена другая Activity (существующая или новая),
    * перекрывшая окно текущей Activity.
    *
    * запись в базу данных
    * приостановка сложной анимации
    * приостановка потоков, отслеживания показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
    * которые нужны исключительно для обновления пользовательского интерфейса
    *
    * Не вызывается при вызове метода finish() у Activity
    * */
    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    /*
    * Метод вызывается по окончании работы Activity, при вызове метода finish() или в случае,
    * когда системы уничтожает этот экземпляр активности для освобождения ресурсов.
    * */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }

    /*
    *  Этот метод сохраняет состояние представления в Bundle
    *  Для API Level < 28 (Android P) этот метод будет выполняться до onStop(), и нет никаких гарантий, что
    *  это произойдет до или после onPause(один вариант произойдет точно)
    *  Для API Level >= 28 будет вызван после onStop()
    *  Не будет вызван, если Activity будет явно закрыто пользователем при нажатии на системную клавишу back
    */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(IS_EDIT_MODE, isEditMode)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it) })
        viewModel.getTheme().observe(this, Observer { updateTheme(it) })
    }

    private fun updateTheme(mode: Int) {
        Log.d("M_ProfileActivity", "updateTheme")
        delegate.setLocalNightMode(mode)
    }

    private fun updateUI(profile: Profile) {
        profile.toMap().also {
            for ((k, v) in viewFields) {
                v.text = it[k].toString()
            }
        }
        val initials = Utils.toInitials(profile.firstName, profile.lastName)
        if (initials.isNullOrEmpty())
            iv_avatar.setImageResource(R.drawable.avatar_default)
        else
            iv_avatar.setImageDrawable(Utils.createDefaultAvatar(initials, this))
    }

    private fun initViews(savedInstanceState: Bundle?) {
        viewFields = mapOf(
            "nickName" to tv_nick_name,
            "rank" to tv_rank,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository,
            "rating" to tv_rating,
            "respect" to tv_respect
        )

        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE) ?: false
        showCurrentMode(isEditMode)

        btn_edit.setOnClickListener {
            if (isEditMode)
                saveProfileInfo()
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }

        btn_switch_theme.setOnClickListener {
            viewModel.switchTheme()
        }

        et_repository.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) {
                if (Profile.validateRepository(p0.toString())) {
                    wr_repository.isErrorEnabled = false
                    wr_repository.error = null
                } else {
                    wr_repository.isErrorEnabled = true
                    wr_repository.error = "Невалидный адрес репозитория"
                }
            }
        })
    }

    private fun showCurrentMode(isEdit: Boolean) {
        val info = viewFields.filter { setOf("firstName", "lastName", "about", "repository").contains(it.key) }
        for ((_, v) in info) {
            v as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if (isEdit) 255 else 0
        }

        ic_eye.visibility = if (isEdit) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = isEdit

        with(btn_edit) {
            val filter: ColorFilter? = if (isEdit) {
                val color = theme.obtainStyledAttributes(listOf(R.attr.colorAccent).toIntArray()).getColor(0, 0)
                PorterDuffColorFilter(
                    color,
                    PorterDuff.Mode.SRC_IN
                )
            } else {
                null
            }
            val icon = if (isEdit) {
                resources.getDrawable(R.drawable.ic_save_black_24dp, theme)
            } else {
                resources.getDrawable(R.drawable.ic_edit_black_24dp, theme)
            }
            background.colorFilter = filter
            setImageDrawable(icon)
        }
        val width = iv_avatar.getBorderWidth()
        iv_avatar.setBorderWidth(width)
    }

    private fun saveProfileInfo() {
        val repository = et_repository.text.toString()
        Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = if (Profile.validateRepository(repository)) repository else ""
        ).apply {
            viewModel.saveProfileData(this)
        }
    }

    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }
}

