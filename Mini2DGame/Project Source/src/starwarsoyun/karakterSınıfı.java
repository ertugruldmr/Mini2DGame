
package starwarsoyun;

import java.util.List;



public class karakterSınıfı {
    String Ad;
    String Tür;
    Lokasyon Yol;

    public karakterSınıfı(String Ad, String Tür, Lokasyon Yol) {
        this.Ad = Ad;
        this.Tür = Tür;
        this.Yol = Yol;
    }
    
    
    public List<Dugum> EnkısaYol(List<Dugum> a,Lokasyon Hedef,int[][]  blocksArray){
      
        return   a;
    }

    public String getAd() {
        return Ad;
    }

    public String getTür() {
        return Tür;
    }

    public Lokasyon getYol() {
        return Yol;
    }

    public void setAd(String Ad) {
        this.Ad = Ad;
    }

    public void setTür(String Tür) {
        this.Tür = Tür;
    }

    public void setYol(Lokasyon Yol) {
        this.Yol = Yol;
    }
    
    
    
    //iyi karakterler icin
    public float getCan(){return (float)0.0;}
    public void setCan(float can){}   
}


class DarthVader extends karakterSınıfı{

    public DarthVader(String Ad, String Tür, Lokasyon Yol) {
        super(Ad, Tür, Yol);
    }

    @Override
    public List<Dugum> EnkısaYol(List<Dugum> kısayol,Lokasyon Hedef,int[][]  blocksArray) {
        int Satır = 14, Stun = 11;
        Dugum   Kovalanan = new Dugum(Hedef.getX(), Hedef.getY());   
        Dugum   Kovalayan = new Dugum(Yol.getX(), Yol.getY());
        AstarEnKısaYol aStar = new AstarEnKısaYol(Satır, Stun,Kovalayan,Kovalanan);
        blocksArray = new int[0][0];    
        aStar.DuvarlarıAta(blocksArray);
        kısayol = aStar.YolBul();
       return kısayol;
    }

}

class KyloRen extends karakterSınıfı{
    
    public KyloRen(String Ad, String Tür, Lokasyon Yol) {
        super(Ad, Tür, Yol);
    }
    @Override
    public List<Dugum> EnkısaYol(List<Dugum> kısayol,Lokasyon Hedef,int[][]  blocksArray) {
        int Satır = 14, Stun = 11;
        Dugum   Kovalanan = new Dugum(Hedef.getX(), Hedef.getY());   
        Dugum   Kovalayan = new Dugum(Yol.getX(), Yol.getY());
        AstarEnKısaYol aStar = new AstarEnKısaYol(Satır, Stun,Kovalayan,Kovalanan); 
        aStar.DuvarlarıAta(blocksArray);
        kısayol = aStar.YolBul();
       return kısayol;
    }

}
class Stormtrooper extends karakterSınıfı{
    
    public Stormtrooper(String Ad, String Tür, Lokasyon Yol) {
        super(Ad, Tür, Yol);
    }

      @Override
    public List<Dugum> EnkısaYol(List<Dugum> kısayol,Lokasyon Hedef,int[][]  blocksArray) {
        int Satır = 14, Stun = 11;
        Dugum   Kovalanan = new Dugum(Hedef.getX(), Hedef.getY());   
        Dugum   Kovalayan = new Dugum(Yol.getX(), Yol.getY());
        AstarEnKısaYol aStar = new AstarEnKısaYol(Satır, Stun,Kovalayan,Kovalanan); 
        aStar.DuvarlarıAta(blocksArray);
        kısayol = aStar.YolBul();
       return kısayol;
    }

}
class MasterYoda extends karakterSınıfı{
    float can=(float) 3.0;
    public MasterYoda(String Ad, String Tür, Lokasyon Yol) {
        super(Ad, Tür, Yol);
    }

    @Override
    public void setCan(float can) {
       this.can=can;
    }

    @Override
    public float getCan() {
         return can;
    }

    
  
}
class LukeSkywalker extends karakterSınıfı{
    float can=(float) 3.0;
    public LukeSkywalker(String Ad, String Tür, Lokasyon Yol) {
        super(Ad, Tür, Yol);
    }

    @Override
    public float getCan() {
        return can;
    }

    @Override
    public void setCan(float can) {
        this.can = can;
    }

   

  



    
    
}
