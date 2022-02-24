package com.desafio.orion.services;

import com.desafio.orion.models.LocalCidade;
import com.desafio.orion.models.Sku;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface SkuService {
    public List<Sku> findAll();
}
