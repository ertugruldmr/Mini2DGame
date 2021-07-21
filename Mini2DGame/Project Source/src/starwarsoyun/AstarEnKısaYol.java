package starwarsoyun;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


class Dugum{
    private int onceki_bedenl;
    private int sonraki_bedel;
    private int toplam_bedel;
    private int satır_sayısı;
    private int stun_sayısı;
    private boolean duvar_var_mı;
    private Dugum komsu;

    public Dugum(int satır_sayısı, int stun_sayısı) {
        this.satır_sayısı = satır_sayısı;
        this.stun_sayısı = stun_sayısı;
    }
    
    public void DugumDegerAta(int Bedel,Dugum Secili_dugum){
        int GuncelBedel = Secili_dugum.getOnceki_bedenl()+ Bedel;
        setKomsu(Secili_dugum);
        setOnceki_bedenl(GuncelBedel);
        ToplamBedeliHesapla();

    }
    
    private void ToplamBedeliHesapla() {
        int ToplamBedel = getOnceki_bedenl()+ getSonraki_bedel();
        setToplam_bedel(ToplamBedel);
    }
    public void SonrakiBedelHesapla(Dugum SonDugum) {
        this.sonraki_bedel = Math.abs(SonDugum.getSatır_sayısı()- getSatır_sayısı()) + Math.abs(SonDugum.getStun_sayısı()- getStun_sayısı());
    }
    
    public boolean YolKontrol(Dugum Secili_dugum, int Bedel) {
        int GuncelBedel = Secili_dugum.getOnceki_bedenl()+ Bedel;
        if (GuncelBedel < getOnceki_bedenl()) {
            DugumDegerAta( Bedel,Secili_dugum);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Dugum [satır_sayısı=" + satır_sayısı + ", stun_sayısı=" + stun_sayısı + "]";
    }

    @Override
    public boolean equals(Object obj) {
        Dugum Diger = (Dugum) obj;
        return this.getStun_sayısı()== Diger.getStun_sayısı() && this.getSatır_sayısı()== Diger.getSatır_sayısı();
    }
    ////SSSSSSSSEEEEEEEETTTTTTTTTEEEERRRSS
    public void setOnceki_bedenl(int onceki_bedenl) {
        this.onceki_bedenl = onceki_bedenl;
    }

    public void setSonraki_bedel(int sonraki_bedel) {
        this.sonraki_bedel = sonraki_bedel;
    }

    public void setToplam_bedel(int toplam_bedel) {
        this.toplam_bedel = toplam_bedel;
    }

    public void setSatır_sayısı(int satır_sayısı) {
        this.satır_sayısı = satır_sayısı;
    }

    public void setStun_sayısı(int stun_sayısı) {
        this.stun_sayısı = stun_sayısı;
    }

    public void setDuvar_var_mı(boolean duvar_var_mı) {
        this.duvar_var_mı = duvar_var_mı;
    }

    public void setKomsu(Dugum komsu) {
        this.komsu = komsu;
    }
    ///GGGGGGGGEEEEEEETTTTTEEEEEERS
    public int getOnceki_bedenl() {
        return onceki_bedenl;
    }

    public int getSonraki_bedel() {
        return sonraki_bedel;
    }

    public int getToplam_bedel() {
        return toplam_bedel;
    }

    public int getSatır_sayısı() {
        return satır_sayısı;
    }

    public int getStun_sayısı() {
        return stun_sayısı;
    }

    public boolean isDuvar_var_mı() {
        return duvar_var_mı;
    }

    public Dugum getKomsu() {
        return komsu;
    }
  
}






public class AstarEnKısaYol {
    private static int Maliyet=10;
    private int DüzBedel;  
    private Dugum[][] Harita;
    private PriorityQueue<Dugum> openList;
    private Set<Dugum> closedSet;
    private Dugum İlkDugum;
    private Dugum SonDugum;

    public AstarEnKısaYol(int satır_sayısı,int stun_sayısı,int DüzBedel, Dugum İlkDugum, Dugum SonDugum) {
        this.DüzBedel = DüzBedel;
        setİlkDugum(İlkDugum);
        setSonDugum(SonDugum);
        
        this.Harita=new Dugum[satır_sayısı][stun_sayısı];
        this.openList = new PriorityQueue<Dugum>(new Comparator<Dugum>() {
            @Override
            public int compare(Dugum node0, Dugum node1) {
                return Integer.compare(node0.getToplam_bedel(), node1.getToplam_bedel());
            }
        });
        DugumlerıAta();
        this.closedSet = new HashSet<>();
    }
    
    public AstarEnKısaYol(int Satır_sayısı,int stun_sayısı,Dugum ilkDugum,Dugum sonDugum){
       this(Satır_sayısı, stun_sayısı, Maliyet, ilkDugum, sonDugum);
        
    }
    private void DugumlerıAta() {
        for (int i = 0; i < Harita.length; i++) {
            for (int j = 0; j < Harita[0].length; j++) {
                Dugum dugum = new Dugum(i, j);
                dugum.SonrakiBedelHesapla(getSonDugum());
                this.Harita[i][j] = dugum;
            }
        }
    }
    
    
    public List<Dugum> YolBul() {
        openList.add(İlkDugum);
        while (!BOS_MU(openList)) {
            Dugum Secili = openList.poll();
            closedSet.add(Secili);
            if (Son_Dugum_mu(Secili)) {
                return YOL_dondur(Secili);
            } else {
                KomsuDugumlerıGez(Secili);
            }
        }
        return new ArrayList<Dugum>();
    }

    private List<Dugum> YOL_dondur(Dugum secili) {
        List<Dugum> Yol = new ArrayList<Dugum>();
        Yol.add(secili);
        Dugum komsu;
        while ((komsu = secili.getKomsu()) != null) {
            Yol.add(0, komsu);
            secili = komsu;
        }
        return Yol;
    }

    private void KomsuDugumlerıGez(Dugum Seicili) {
        UstKomsuSatır(Seicili);
        OrtaKomsuSatır(Seicili);
        AltKomsuSatır(Seicili);
    }

    private void AltKomsuSatır(Dugum Secili) {
        int Satır_sayısı = Secili.getSatır_sayısı();
        int Stun_sayısı = Secili.getStun_sayısı();
        int AltSatır = Satır_sayısı + 1;
        if (AltSatır < getHarita().length) {
            DugumKontrol(Secili, Stun_sayısı, AltSatır, getDüzBedel());
        }
    }

    private void OrtaKomsuSatır(Dugum Secili) {
        int Satır_sayısı = Secili.getSatır_sayısı();
        int Stun_sayısı = Secili.getStun_sayısı();
        int OrtaSatır = Satır_sayısı;
        if (Stun_sayısı - 1 >= 0) {
            DugumKontrol(Secili, Stun_sayısı - 1, OrtaSatır, getDüzBedel());
        }
        if (Stun_sayısı + 1 < getHarita()[0].length) {
            DugumKontrol(Secili, Stun_sayısı + 1, OrtaSatır, getDüzBedel());
        }
    }

    private void UstKomsuSatır(Dugum Secili) {
        int Satır_sayısı = Secili.getSatır_sayısı();
        int Stun_sayısı = Secili.getStun_sayısı();
        int UstSatır = Satır_sayısı - 1;
        if (UstSatır >= 0) {
            DugumKontrol(Secili, Stun_sayısı, UstSatır, getDüzBedel());
        }
    }

    private void DugumKontrol(Dugum Secili, int Stun, int Satır, int Bedel) {
        Dugum KomsuDugum = getHarita()[Satır][Stun];
        if (!KomsuDugum.isDuvar_var_mı()&& !getClosedSet().contains(KomsuDugum)) {
            if (!getOpenList().contains(KomsuDugum)) {
                KomsuDugum.DugumDegerAta(Bedel, Secili);
                getOpenList().add(KomsuDugum);
            } else {
                boolean degişsin_mi = KomsuDugum.YolKontrol(Secili, Bedel);
                if (degişsin_mi) {
                   //Geri don (sil) ve yenı yola git(ekle)
                    getOpenList().remove(KomsuDugum);
                    getOpenList().add(KomsuDugum);
                }
            }
        }
    } 
     public void DuvarlarıAta(int[][] Duvar) {
        for (int i = 0; i < Duvar.length; i++) {
            int Satır = Duvar[i][0];
            int Stun = Duvar[i][1];
            duvarKUR(Satır, Stun);
        }
    }

      private void duvarKUR(int Satır, int Stun) {
        this.Harita[Satır][Stun].setDuvar_var_mı(true);
    }
     private boolean Son_Dugum_mu(Dugum Secili) {
        return Secili.equals(SonDugum);
    }

    private boolean BOS_MU(PriorityQueue<Dugum> openList) {
        return openList.size() == 0;
    }
     

    public static int getMaliyet() {
        return Maliyet;
    }

    public int getDüzBedel() {
        return DüzBedel;
    }

    public Dugum[][] getHarita() {
        return Harita;
    }

    public PriorityQueue<Dugum> getOpenList() {
        return openList;
    }

    public Set<Dugum> getClosedSet() {
        return closedSet;
    }

    public Dugum getİlkDugum() {
        return İlkDugum;
    }

    public Dugum getSonDugum() {
        return SonDugum;
    }

    public static void setMaliyet(int Maliyet) {
        AstarEnKısaYol.Maliyet = Maliyet;
    }

    public void setDüzBedel(int DüzBedel) {
        this.DüzBedel = DüzBedel;
    }

    public void setHarita(Dugum[][] Harita) {
        this.Harita = Harita;
    }

    public void setOpenList(PriorityQueue<Dugum> openList) {
        this.openList = openList;
    }

    public void setClosedSet(Set<Dugum> closedSet) {
        this.closedSet = closedSet;
    }

    public void setİlkDugum(Dugum İlkDugum) {
        this.İlkDugum = İlkDugum;
    }

    public void setSonDugum(Dugum SonDugum) {
        this.SonDugum = SonDugum;
    }  
}
