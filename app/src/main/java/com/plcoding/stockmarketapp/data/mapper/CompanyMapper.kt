package com.plcoding.stockmarketapp.data.mapper

import com.plcoding.stockmarketapp.data.local.CompanyListingEntity
import com.plcoding.stockmarketapp.data.remote.dto.CompanyInfoDTO
import com.plcoding.stockmarketapp.domain.model.CompanyInfo
import com.plcoding.stockmarketapp.domain.model.CompanyListing


fun CompanyListingEntity.toCompanyListing() : CompanyListing{
    return  CompanyListing(
        name= name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity() : CompanyListingEntity{
    return  CompanyListingEntity(
        name= name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDTO.toCompanyInfo(): CompanyInfo{
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "" ,
        name= name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}