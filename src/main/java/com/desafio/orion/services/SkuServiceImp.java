package com.desafio.orion.services;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.LocalCidade;
import com.desafio.orion.models.Sku;
import com.desafio.orion.models.SkuDTO;
import com.desafio.orion.repository.LocalCidadeRepository;
import com.desafio.orion.repository.SkuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SkuServiceImp implements SkuService {
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private LocalCidadeRepository localCidadeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Utils util = new Utils();

    @Transactional
    public void salvarNovoSku(SkuDTO skuDTO) {
        LocalCidade localCidade = new LocalCidade();
        Sku sku = new Sku();
        skuDTO.setUnixTime(Instant.now().getEpochSecond());
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        modelMapper.map(skuDTO, localCidade);
        modelMapper.map(skuDTO, sku);
        sku.setLocalCidade(localCidade);
        localCidade.setSku(sku);
        localCidadeRepository.save(localCidade);
        skuRepository.save(sku);
    }


    public LocalCidade getLocalCidadeByUnix(long unix) {
        Optional<LocalCidade> localCidade = localCidadeRepository.findByUnixtime(unix);
        return localCidade.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o Unix Time: " + unix));
    }

    public SkuDTO dbToDTO(Sku sku) {
        Utils utils = new Utils();
        SkuDTO skuDTO = utils.conversorSkuParaSkuDto(sku.getSkuString());
        LocalCidade localCidade = sku.getLocalCidade();
        modelMapper.map(localCidade, skuDTO);
        return skuDTO;
    }

    public void deletar(long id) {
        Optional<Sku> sku = skuRepository.findById(id);
        sku.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o sku: " + id));
        skuRepository.delete(sku.get());
    }


    public Sku findBySku(String sku) {
        Optional<Sku> skuParsed = skuRepository.findBySkuString(sku);
        return skuParsed.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o sku: " + sku));

    }
    public List<SkuDTO> findAlltoDTO() {
        List<Sku> listaSku = findAll();
        List<SkuDTO> listaDto = new ArrayList<>();
        for(Sku dto: listaSku){
            listaDto.add(dbToDTO(dto));
        }
        return listaDto;
    }

    @Override
    public List<Sku> findAll() {

        return skuRepository.findAll();
    }

}




