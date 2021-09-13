package com.yurry.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<RequestType, ResultType> {
    private var result: Flow<Resource<ResultType>> = flow {

    }
}