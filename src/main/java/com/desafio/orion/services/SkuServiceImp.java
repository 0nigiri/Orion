package com.desafio.orion.services;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.LocalCidade;
import com.desafio.orion.models.Sku;
import com.desafio.orion.models.SkuDTO;
import com.desafio.orion.models.User;
import com.desafio.orion.repository.LocalCidadeRepository;
import com.desafio.orion.repository.SkuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private UserServiceImp userService;

    @Autowired
    private ModelMapper modelMapper;

    public Utils util = new Utils();

    @Transactional
    public void salvarNovoSku(SkuDTO skuDTO) {
        LocalCidade localCidade = new LocalCidade();
        Sku sku = new Sku();
        User user = userService.getUserByUsername(skuDTO.getUsername());
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        modelMapper.map(skuDTO, localCidade);
        modelMapper.map(skuDTO, sku);
        sku.setLocalCidade(localCidade);
        localCidade.setSku(sku);
        localCidade.setUser(user);
        skuRepository.save(sku);
        localCidadeRepository.save(localCidade);
    }


    public LocalCidade getLocalCidadeByUnix(long unix) {
        Optional<LocalCidade> localCidade = localCidadeRepository.findByUnixtime(unix);
        return localCidade.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o Unix Time: " + unix));
    }

    public SkuDTO dbToDTO(Sku sku) {
        Utils utils = new Utils();
        SkuDTO skuDTO = utils.conversorSkuParaSkuDto(sku.getSkuString());
        LocalCidade localCidade = sku.getLocalCidade();
        User user = localCidade.getUser();
        modelMapper.map(user, skuDTO);
        modelMapper.map(localCidade, skuDTO);
        return skuDTO;
    }



    public Sku findBySku(String sku) {
        Optional<Sku> skuParsed = skuRepository.findBySkuString(sku);
        return skuParsed.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o sku: " + sku));
    }


    public List<SkuDTO> findAlltoDTO() {
        List<Sku> listaSku = findAll();
        List<SkuDTO> listaDto = new ArrayList<>();
        for (Sku dto : listaSku) {
            listaDto.add(dbToDTO(dto));
        }
        return listaDto;
    }

    public List<LocalCidade> findListLocalCidade() {
        return localCidadeRepository.findAll();
    }


    @Override
    public List<Sku> findAll() {

        return skuRepository.findAll();
    }

    @Override
    public Sku findById(long id) {
        Optional<Sku> skuParsed = skuRepository.findById(id);
        return skuParsed.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o id " + id));
    }

    public void deleteLocalCidade(long id) {
        localCidadeRepository.deleteById(id);
    }

    public boolean skuExists(String sku) {
        return skuRepository.findBySkuString(sku).isPresent();
    }

}




