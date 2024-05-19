package com.huntercodexs.demo.enumerator;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum UfTable {
    AC(12, "Acre", "Acre", "SSPAC"),
    AL(27, "Alagoas", "Alagoas", "SSPAL"),
    AM(13, "Amazonas", "Amazonas", "SSPAM"),
    AP(16, "Amapá", "Amapa", "SSPAP"),
    BA(29, "Bahia", "Bahia", "SSPBA"),
    CE(23, "Ceará", "Ceara", "SSPCE"),
    DF(53, "Distrito Federal", "Distrito Federal", "SSPDF"),
    ES(32, "Espírito Santo", "Espirito Santo", "SSPES"),
    GO(52, "Goiás", "Goias", "SSPGO"),
    MA(21, "Maranhão", "Maranhao", "SSPMA"),
    MG(31, "Minas Gerais", "Minas Gerais", "SSPMG"),
    MS(50, "Mato Grosso do Sul", "Mato Grosso do Sul", "SSPMS"),
    MT(51, "Mato Grosso", "Mato Grosso", "SSPMT"),
    PA(15, "Pará", "Para", "SSPPA"),
    PB(25, "Paraíba", "Paraiba", "SSPPB"),
    PE(26, "Pernambuco", "Pernambuco", "SSPPE"),
    PI(22, "Piauí", "Piaui", "SSPPI"),
    PR(41, "Paraná", "Parana", "SSPPR"),
    RJ(33, "Rio de Janeiro", "Rio de Janeiro", "SSPRJ"),
    RN(24, "Rio Grande do Norte", "Rio Grande do Norte", "SSPRN"),
    RO(11, "Rondônia", "Rondonia", "SSPRO"),
    RR(14, "Roraima", "Roraima", "SSPRR"),
    RS(43, "Rio Grande do Sul", "Rio Grande do Sul", "SSPRS"),
    SC(42, "Santa Catarina", "Santa Catarina", "SSPSC"),
    SE(28, "Sergipe", "Sergipe", "SSPSE"),
    SP(35, "São Paulo", "Sao Paulo", "SSPSP"),
    TO(17, "Tocantins", "Tocantins", "SSPTO");

    private int ufCode;
    private String stateName;
    private String stateNameClear; /*Nome do estado sem acento*/
    private String rgsSsp; /*Nome do estado sem acento*/

    UfTable(int ufCode, String stateName, String stateNameClear, String rgsSsp) {
        this.ufCode = ufCode;
        this.stateName = stateName;
        this.stateNameClear = stateNameClear;
        this.rgsSsp = rgsSsp;
    }

    public static int ufCode(String uf) {
        return UfTable.valueOf(uf).getUfCode();
    }

    public static String stateName(String uf) {
        return UfTable.valueOf(uf).getStateName();
    }

    public static String stateNameClear(String uf) {
        return UfTable.valueOf(uf).getStateNameClear();
    }

    public static String rgSspCode(String uf) {
        return UfTable.valueOf(uf).getRgsSsp();
    }

    public static boolean checkUfExists(String uf) {
        for (UfTable value : UfTable.values()) {
            if (value.name().equals(uf)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUfCodeExists(String code) {
        for (UfTable value : UfTable.values()) {
            if (String.valueOf(value.getUfCode()).equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUfNameExists(String ufName) {
        for (UfTable value : UfTable.values()) {
            if (value.getStateName().equals(ufName) || value.getStateNameClear().equals(ufName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRgSspExists(String rgsSsp) {
        for (UfTable value : UfTable.values()) {
            if (value.getRgsSsp().equals(rgsSsp)) {
                return true;
            }
        }
        return false;
    }
}
