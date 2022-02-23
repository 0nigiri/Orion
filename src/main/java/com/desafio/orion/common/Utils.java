package com.desafio.orion.common;

import com.desafio.orion.models.SkuDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String passwordEncoder(String password) {
        String encodedPassword = encoder.encode(password);
        return encodedPassword;
    }

    public String conversorSkuDtoParaSku(SkuDTO skuDTO) {
        String skuString = "";

        switch (skuDTO.getDistribuidora()) {
            case "Estados Unidos":
                skuString += "US";
                break;
            case "Mexico":
                skuString += "MX";
                break;
            case "Chile":
                skuString += "CH";
                break;
        }
        switch (skuDTO.getLingua()) {
            case "Ingles":
                skuString += "EN";
                break;
            case "Espanhol":
                skuString += "ES";
                break;
        }
        switch (skuDTO.getContrato()) {
            case "Jogos Multiplos":
                skuString += "JM";
                break;
            case "Unico jogo":
                skuString += "UJ";
                break;
        }
        switch (skuDTO.getQuantidadePlacas()) {
            case 1:
                skuString += "01";
                break;
            case 2:
                skuString += "02";
                break;
            case 3:
                skuString += "03";
                break;
            case 4:
                skuString += "04";
                break;
            case 5:
                skuString += "05";
                break;
            case 6:
                skuString += "06";
                break;
            case 7:
                skuString += "07";
                break;
            case 8:
                skuString += "08";
                break;
            case 9:
                skuString += "09";
                break;
            case 10:
                skuString += "10";
                break;
        }
        switch (skuDTO.getNumeroJogos()) {
            case 1:
                skuString += "01";
                break;
            case 2:
                skuString += "02";
                break;
            case 3:
                skuString += "03";
                break;
            case 4:
                skuString += "04";
                break;
            case 5:
                skuString += "05";
                break;
        }
        for (String jogos : skuDTO.getJogos()) {
            //"Halloween", "Valentine's day", "Easter Sunday", "New Year"
            // "Lunar New Year", "Thanksgiving", "Día de Muertos"
            switch (jogos) {
                case "Halloween":
                    skuString += "H";
                    break;
                case "Valentine's day":
                    skuString += "V";
                    break;
                case "Easter Sunday":
                    skuString += "E";
                    break;
                case "New Year":
                    skuString += "N";
                    break;
                case "Lunar New Year":
                    skuString += "L";
                    break;
                case "Thanksgiving":
                    skuString += "T";
                    break;
                case "Día de Muertos":
                    skuString += "D";
                    break;
            }
        }
        skuString += String.valueOf(skuDTO.getUnixTime());





        return skuString;
    }

    public SkuDTO conversorSkuParaSkuDto(String skuString) {
        List<String> splitSku = splitStringEvery(skuString);
        SkuDTO skuDTO = new SkuDTO();
        switch (splitSku.get(0)) {
            case "US":
                skuDTO.setDistribuidora("Estados Unidos");
                break;
            case "MX":
                skuDTO.setDistribuidora("Mexico");
                break;
            case "CH":
                skuDTO.setDistribuidora("Chile");
                break;
        }
        switch (splitSku.get(1)) {
            case "EN":
                skuDTO.setLingua("Ingles");
                break;
            case "ES":
                skuDTO.setLingua("Espanhol");
                break;
        }
        switch (splitSku.get(2)) {
            case "JM":
                skuDTO.setContrato("Jogos Multiplos");
                skuDTO.setPorcentagem("30%");
                break;
            case "UJ":
                skuDTO.setContrato("Unico jogo");
                skuDTO.setPorcentagem("10%");
                break;
        }
        skuDTO.setQuantidadePlacas(Integer.parseInt(splitSku.get(3)));
        skuDTO.setNumeroJogos(Integer.parseInt(splitSku.get(4)));
        List<String> jogos = new ArrayList<>();
        for(int i= 5; i <=5+skuDTO.getNumeroJogos();i++){
            switch (splitSku.get(i)) {
                case "H":
                    jogos.add("Halloween");
                    break;
                case "V":
                    jogos.add("Valentine's day");
                    break;
                case "E":
                    jogos.add("Easter Sunday");
                    break;
                case "N":
                    jogos.add("New Year");
                    break;
                case "L":
                    jogos.add("Lunar New Year");
                    break;
                case "T":
                    jogos.add("Thanksgiving");
                    break;
                case "D":
                    jogos.add("HallowDía de Muertoseen");
                    break;
            }




        }
        skuDTO.setJogos(jogos);
        skuDTO.setUnixTime(Long.parseLong(splitSku.get(splitSku.size()-1)));

        return skuDTO;

    }

    public List<String> splitStringEvery(String s) {
        List<String> result = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < 5; i++) {
            result.add(s.substring(j, j + 2));
            j += 2;
        }
        int numeroJogos = Integer.parseInt(result.get(4));
        for (int i = 5; i < 5 + numeroJogos; i++) {
            result.add(s.substring(j, j + 1));
            j++;
        }
        result.add(s.substring(j));

        return result;
    }


}
