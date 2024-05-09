package com.loken.passwordmanager.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loken.passwordmanager.data.repo.PasswordRepository
import com.loken.passwordmanager.model.Password
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val passwordRepository: PasswordRepository):ViewModel() {
    private val _homeState = MutableStateFlow(HomeScreenState())
    val homeState: StateFlow<HomeScreenState> = _homeState.asStateFlow()


    fun setEditPasswordData(password: Password) {
        Log.d("ok", "setEditPasswordData: $password")
        _homeState.update {
            it.copy(
                id = password.id,
                accountName = password.accountName.orEmpty(),
                email = password.email.orEmpty(),
                password = password.password.orEmpty(),
            )
        }
    }



init {
    getAllPassword()
}
    fun onAccountNameChange(accountName:String){
        _homeState.update {
            it.copy(accountName =accountName)
        }

    }
    fun onEmailValueChange(email:String){
        _homeState.update {
            it.copy(email =email)
        }

    }
    fun onPasswordValueChange(password:String){
        _homeState.update {
            it.copy(password =password)
        }
    }


    private fun getAllPassword(){
        viewModelScope.launch {
            try {
                passwordRepository.getAllPasswords().collect{passwordDataList->
                    _homeState.update {
                        it.copy(passwordData = passwordDataList)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }

    private fun savePasswordDetails(){
        viewModelScope.launch {
            try {
                passwordRepository.insertPasswordItem(Password(
                    accountName = homeState.value.accountName,
                    email = homeState.value.email,
                    password = homeState.value.password
                )).also {
                    _homeState.update {
                        it.copy(
                            supportTextMessagePassword = null,
                            supportTextMessageEmail = null,
                            supportTextMessageAccount = null,
                            accountName = "",
                            password = "",
                            email = "",
                            isAddUpdateSuccess = true
                        ).also {
                            getAllPassword()
                        }
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }
    private fun deletePasswordDetails(id: Int?){
        viewModelScope.launch {
            try {
                if (id != null) {
                    passwordRepository.deletePasswordItem(id).also {
                        getAllPassword()
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }
    fun resetSuccess(){
        _homeState.update {
            it.copy(isAddUpdateSuccess = false)
        }
    }
    private fun editPasswordDetails(id: Int){
        viewModelScope.launch {
            try {
                passwordRepository.updatePasswordDetails(
                    id = id,
                    accountName = homeState.value.accountName.orEmpty(),
                    email = homeState.value.email.orEmpty(),
                    password = homeState.value.password.orEmpty()
                ).also {
                    _homeState.update {
                        it.copy(
                            supportTextMessagePassword = null,
                            supportTextMessageEmail = null,
                            supportTextMessageAccount = null,
                            accountName = "",
                            password = "",
                            email = "",
                            isAddUpdateSuccess = true
                        ).also {
                            getAllPassword()
                        }
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    private fun getPasswordDetails(id:Int?){
        viewModelScope.launch {
            try {
                if (id != null) {
                    passwordRepository.getPasswordDetails(id = id).collect{passwordDetailsData->
                        _homeState.update {
                            Log.d("viewModel", "getPasswordDetails: $id $passwordDetailsData")
                            it.copy(passwordDetails = passwordDetailsData)
                        }

                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    
    fun  resetState(){
        _homeState.update { 
            it.copy(
                id = null,
                accountName = "",
                email = "",
                password = "",
            )
        }
    }

    fun savePassword(id: Int?) {
        when {
            homeState.value.accountName?.isEmpty() == true -> {
                _homeState.update {
                    it.copy(supportTextMessageAccount = "Enter account name", supportTextMessagePassword = null, supportTextMessageEmail = null)
                }
                return
            }
            homeState.value.email?.isEmpty() == true -> {
                _homeState.update {
                    it.copy(supportTextMessageEmail = "Enter email", supportTextMessageAccount = null, supportTextMessagePassword = null)
                }
                return
            }
            homeState.value.password?.isEmpty() == true || homeState.value.password?.isBlank()== true -> {
                _homeState.update {
                    it.copy(supportTextMessagePassword = "Enter password", supportTextMessageAccount = null, supportTextMessageEmail = null)
                }
                return
            }
            else -> {
                if (id==null){
                    savePasswordDetails()
                }else{
                    updatePasswordDetails(id)

                }
//                _homeState.update {
//                    it.copy(
//                        supportTextMessagePassword = null,
//                        supportTextMessageEmail = null,
//                        supportTextMessageAccount = null,
//                        accountName = "",
//                        password = "",
//                        email = "",
//                        isAddUpdateSuccess = true
//                    ).also {
//
//                    }
//                }
            }
        }
    }

    fun fetchPasswordDetails(id: Int?){
        getPasswordDetails(id = id)
    }
    fun deletePasswordItem(id: Int?){
        deletePasswordDetails(id)
    }
    private fun updatePasswordDetails(id: Int){
        editPasswordDetails(id = id)
    }

}