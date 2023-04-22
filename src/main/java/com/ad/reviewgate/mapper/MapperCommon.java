package com.ad.reviewgate.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.Type;

public class MapperCommon<S, D> {

    @Autowired
    @Qualifier("modelMapperBean")
    private ModelMapper modelMapperBean;
    private Type sourceType;
    private Type destinationType;

    public MapperCommon(Class<S> modelClazz, Class<D> dtoClass) {
        this.sourceType = modelClazz;
        this.destinationType = dtoClass;
    }

    public D toDTO(S inputObject) {
        return modelMapperBean.map(inputObject, destinationType);
    }

    public S toModel(D modelObject) {
        return modelMapperBean.map(modelObject, sourceType);
    }

    protected ModelMapper getModelMapper() {
        return modelMapperBean;
    }

}
