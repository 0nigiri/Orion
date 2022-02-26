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
        int jogosCount = 0;

        switch (skuDTO.getDistribuidora()) {
            case "US":
            case "Estados Unidos":
                skuString += "US";
                break;
            case "MX":
            case "México":
                skuString += "MX";
                break;
            case "CH":
            case "Chile":
                skuString += "CH";
                break;
        }
        switch (skuDTO.getLingua()) {
            case "EN":
            case "Inglês":
                skuString += "EN";
                break;
            case "ES":
            case "Espanhol":
                skuString += "ES";
                break;
        }
        switch (skuDTO.getContrato()) {
            case "MJ":
            case "Múltiplos Jogos":
                skuString += "MJ";
                break;
            case "UJ":
            case "Único Jogo":
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
            if (skuDTO.getNumeroJogos() > jogosCount) {
                switch (jogos) {
                    case "H":
                    case "Halloween":
                        skuString += "H";
                        jogosCount++;
                        break;
                    case "V":
                    case "Valentine's day":
                        skuString += "V";
                        jogosCount++;
                        break;
                    case "E":
                    case "Easter Sunday":
                        skuString += "E";
                        jogosCount++;
                        break;
                    case "N":
                    case "New Year":
                        skuString += "N";
                        jogosCount++;
                        break;
                    case "L":
                    case "Lunar New Year":
                        skuString += "L";
                        jogosCount++;
                        break;
                    case "T":
                    case "Thanksgiving":
                        skuString += "T";
                        jogosCount++;
                        break;
                    case "D":
                    case "Día de Muertos":
                        skuString += "D";
                        jogosCount++;
                        break;
                }
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
                skuDTO.setDistribuidora("US");
                break;
            case "MX":
                skuDTO.setDistribuidora("MX");
                break;
            case "CH":
                skuDTO.setDistribuidora("CH");
                break;
        }
        switch (splitSku.get(1)) {
            case "EN":
                skuDTO.setLingua("EN");
                break;
            case "ES":
                skuDTO.setLingua("ES");
                break;
        }
        switch (splitSku.get(2)) {
            case "MJ":
                skuDTO.setContrato("MJ");
                skuDTO.setPorcentagem("30%");
                break;
            case "UJ":
                skuDTO.setContrato("UJ");
                skuDTO.setPorcentagem("10%");
                break;
        }
        skuDTO.setQuantidadePlacas(Integer.parseInt(splitSku.get(3)));
        skuDTO.setNumeroJogos(Integer.parseInt(splitSku.get(4)));
        List<String> jogos = new ArrayList<>();
        for (int i = 5; i <= 5 + skuDTO.getNumeroJogos(); i++) {
            //"Halloween", "Valentine's day", "Easter Sunday", "New Year"
            // "Lunar New Year", "Thanksgiving", "Día de Muertos"
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
                    jogos.add("Día de Muertos");
                    break;
            }


        }
        skuDTO.setJogos(jogos);
        for (int i = 5 + skuDTO.getNumeroJogos(); i < splitSku.size(); i++) {
            skuDTO.setUnixTime(Long.parseLong(splitSku.get(i)));

        }
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
    //"Halloween", "Valentine's day", "Easter Sunday", "New Year"
    // "Lunar New Year", "Thanksgiving", "Día de Muertos"

    public List<String> listaJogos() {
        List<String> listaJogos = new ArrayList<>();
        listaJogos.add("Halloween");
        listaJogos.add("Valentine's day");
        listaJogos.add("Easter Sunday");
        listaJogos.add("New Year");
        listaJogos.add("Lunar New Year");
        listaJogos.add("Thanksgiving");
        listaJogos.add("Día de Muertos");
        return listaJogos;

    }

}
