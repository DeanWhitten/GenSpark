package com.example.humansvsgoblins_android;

public class Goblin extends Humanoid {
    public Goblin(String name){
        super(name, 100, 10);
    }
    static String generateGoblinName() {
        String gobNames ="Bhaftaaz,Criotnalmee,Duzz,Gliokkolsia,Gryh,Hokoiszea,Khinkea,Sharx,Tamif,Big Ears,Caatterd,Drarm,Grutaat,Huntero,Oiq,Pariah,Riobs,Srebnalk,Vic,Xiagluld,Crimson Hat,Fugh,Grishnar,Gully,patriarch,Huro-Huro,Iz Iz,Kalo,Moka,Pakpak,Shake Spear,Tik,Trizick,Uur'lok,Yameeka,Zarl,Donais,Gazlowe,Grizzle,Helix,Jastor,Kryll,Marin,Mogul,Neesa,Pauli,Pozzik,Revilgaz,Rixxa,Korea,Cham,Gae,dokkaebi,Gaksi,Gim,Seobang,dokkaebi,Oedari";
        String[] namearr =  gobNames.split(",") ;
        int randomNameIndex = (int) ((Math.random() * namearr.length) + 1);

        return namearr[randomNameIndex];
    }

}


