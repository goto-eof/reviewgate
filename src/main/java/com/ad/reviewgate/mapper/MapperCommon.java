package com.ad.reviewgate.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public class MapperCommon<S, D> {

    @Autowired
    private ModelMapper modelMapper;
    private Type sourceType;
    private Type destinationType;

    public MapperCommon(Class<S> modelClazz, Class<D> dtoClass) {
        this.sourceType = modelClazz;
        this.destinationType = dtoClass;
    }

    public D toDTO(S inputObject) {
        return modelMapper.map(inputObject, destinationType);
    }

    public S toModel(D modelObject) {
        return modelMapper.map(modelObject, sourceType);
    }

    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

}
