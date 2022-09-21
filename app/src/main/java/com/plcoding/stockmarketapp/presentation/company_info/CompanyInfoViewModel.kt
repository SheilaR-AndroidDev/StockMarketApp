package com.plcoding.stockmarketapp.presentation.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.domain.repository.StocksRepositoryI
import com.plcoding.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, //for navigation args
    private val repositoryI: StocksRepositoryI
) : ViewModel(){

    var state by mutableStateOf(CompanyInfoState())

    init{
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch

            state = state.copy(isLoading = true)

            val companyInfoResult = async{ repositoryI.getCompanyInfo(symbol)} // to work independently

            val intraDayInfoResult = async { repositoryI.getIntraDayInfo(symbol)}

            when(val infoResult = companyInfoResult.await()){
                is Resource.Success -> {
                    state.copy(company = infoResult.data,
                               isLoading = false,
                               error = null)

                }
                is Resource.Error ->{
                    state.copy(error = infoResult.message,
                               isLoading = false,
                               company = null)
                }
                else -> Unit
            }

            when(val intraResult = intraDayInfoResult.await()){
                is Resource.Success -> {
                    state.copy(stockInfo = intraResult.data ?: emptyList(),
                        isLoading = false,
                        error = null)

                }
                is Resource.Error ->{
                    state.copy(error = intraResult.message,
                        isLoading = false,
                        company = null)
                }
                else -> Unit
            }
        }
    }
}