package starwarsoyun;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Lokasyon {

    private int x;
    private int y;

    public Lokasyon(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}

class karakterListesi {

    private String karakter_turu;
    private Lokasyon konum;

    public karakterListesi(String karakter_turu, Lokasyon konum) {
        this.karakter_turu = karakter_turu;
        this.konum = konum;
    }

    public String getKarakter_turu() {
        return karakter_turu;
    }

    public Lokasyon getKonum() {
        return konum;
    }

    public void setKarakter_turu(String karakter_turu) {
        this.karakter_turu = karakter_turu;
    }

    public void setKonum(Lokasyon konum) {
        this.konum = konum;
    }

}

public class StarWarsOyun extends JFrame {

    ArrayList<karakterListesi> karakter = new ArrayList<karakterListesi>();
    ArrayList<String> harita = new ArrayList<String>();
    String[][] HaritaMatris = new String[11][14];

    List<Dugum> k1path;
    List<Dugum> k2path;

    Lokasyon A = new Lokasyon(0, 5);
    Lokasyon B = new Lokasyon(4, 0);
    Lokasyon C = new Lokasyon(12, 0);
    Lokasyon D = new Lokasyon(13, 5);
    Lokasyon E = new Lokasyon(4, 10);
    Lokasyon L = new Lokasyon(6, 5);

    int Start_Kotu1_x, Start_Kotu1_Y, Start_Kotu2_x, Start_Kotu2_Y;

    karakterSınıfı iyi_karakter;
    karakterSınıfı kotu1_karakter;
    karakterSınıfı kotu2_karakter;

    BufferedImage A_icon, B_icon, C_icon, D_icon, E_icon, kupa_icon;
    BufferedImage YODA_icon, skywalker_icon, darthVader_icon, Stormtrooper_icon, KyloRen_icon;
    BufferedImage iyi_icon, kotu1_icon, kotu2_icon;
    BufferedImage can, can_3_0, can_2_5, can_2_0, can_1_5, can_1_0, can_0_5;

    JFrame window;
    Container con;
    JPanel titleNamePanel, YODAbuttonPanel, SKYWALKERbuttonPanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Terminator Two", Font.PLAIN, 24);
    JButton YODAbutton, SKYWALKERbutton;

    YodaAction YODA_tik = new YodaAction();
    SkaywlakerAction SKAYWALKER_tik2 = new SkaywlakerAction();
    klavye_girdisi girdi = new klavye_girdisi();

    ImageIcon YODAIcon;
    ImageIcon SKYWALKERIcon;

    @Override
    public void paint(Graphics g) {

        g.setColor(new Color(150, 190, 190));
        g.fillRect(0, 0, 800, 600);

        //Harita için kareler
        int x = 0, y = 0;
        for (String a : harita) {
            if (a.equalsIgnoreCase("1")) {
                g.setColor(new Color(150, 190, 190));
                g.fillRect(130 + x * 40, 100 + y * 40, 40, 40);
            }
            if (a.equalsIgnoreCase("0")) {
                g.setColor(Color.white);
                g.fillRect(130 + x * 40, 100 + y * 40, 40, 40);
            }

            x++;
            if (x % 14 == 0) {
                x = 0;
                y++;
            }
        }

        //girisler ve kupa         
        g.drawImage(A_icon, 130 + 0 * 40, 100 + 5 * 40, null);
        g.drawImage(B_icon, 130 + 4 * 40, 100 + 0 * 40, null);
        g.drawImage(C_icon, 130 + 12 * 40, 100 + 0 * 40, null);
        g.drawImage(D_icon, 130 + 13 * 40, 100 + 5 * 40, null);
        g.drawImage(E_icon, 130 + 4 * 40, 100 + 10 * 40, null);
        g.drawImage(kupa_icon, 135 + 14 * 40, 95 + 9 * 40, null);
        //karakterin basladıgı sarı nokta
        g.setColor(Color.YELLOW);
        g.fillRect(130 + 6 * 40, 100 + 5 * 40, 40, 40);
        //can barı
        g.drawImage(can, 600, 35, null);

        g.setColor(Color.red);
//en kısa yol
        for (Dugum dugum1 : k1path) {
            g.fillRect(130 + dugum1.getSatır_sayısı() * 40, 100 + dugum1.getStun_sayısı() * 40, 40, 40);
        }
        if (kotu2_karakter != null) {
            g.setColor(Color.red);
            for (Dugum dugum2 : k2path) {
                g.fillRect(130 + dugum2.getSatır_sayısı() * 40, 100 + dugum2.getStun_sayısı() * 40, 40, 40);
            }
        }

        //karakterler
        g.drawImage(iyi_icon, 130 + iyi_karakter.getYol().getX() * 40, 100 + iyi_karakter.getYol().getY() * 40, null);
        g.drawImage(kotu1_icon, 130 + kotu1_karakter.getYol().getX() * 40, 100 + kotu1_karakter.getYol().getY() * 40, null);
        if (kotu2_karakter != null) {
            g.drawImage(kotu2_icon, 130 + kotu2_karakter.getYol().getX() * 40, 100 + kotu2_karakter.getYol().getY() * 40, null);
        }

        //çizgiler  
        g.setColor(Color.black);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 15; j++) {
                g.drawLine(130 + j * 40, 100, 130 + j * 40, 540);
                g.drawLine(130, 100 + i * 40, 690, 100 + i * 40);
            }
        }

        window.setVisible(true);
    }

    @Override
    public void repaint() {
        paint(window.getGraphics());
    }

    public static void main(String[] args) {
        StarWarsOyun a = new StarWarsOyun();
        a.TextOkuyucu();
    }

    public void resimleri_yukle() {
        
        try {
            //giris ikonları ve kupa
            A_icon = ImageIO.read(new File("images\\doors\\Agirisi.png"));
            B_icon = ImageIO.read(new File("images\\doors\\Bgirisi.png"));
            C_icon = ImageIO.read(new File("images\\doors\\Cgirisi.png"));
            D_icon = ImageIO.read(new File("images\\doors\\Dgirisi.png"));
            E_icon = ImageIO.read(new File("images\\doors\\Egirisi.png"));
            kupa_icon = ImageIO.read(new File("images\\doors\\kupa.png"));
            //karakter ikonları
            YODA_icon = ImageIO.read(new File("images\\icons\\YODA_icon.png"));
            skywalker_icon = ImageIO.read(new File("images\\icons\\skywalker_icon.png"));
            darthVader_icon = ImageIO.read(new File("images\\icons\\darthVader_icon.png"));
            Stormtrooper_icon = ImageIO.read(new File("images\\icons\\Stormtrooper_icon.png"));
            KyloRen_icon = ImageIO.read(new File("images\\icons\\KyloRen_icon.png"));
            //can resimleri
            // can,can_3_0,can_2_5,can_2_0,can_1_5,can_1_0,can_0_5

            can_3_0 = ImageIO.read(new File("images\\can\\3.0.png"));
            can_2_5 = ImageIO.read(new File("images\\can\\2.5.png"));
            can_2_0 = ImageIO.read(new File("images\\can\\2.0.png"));
            can_1_5 = ImageIO.read(new File("images\\can\\1.5.png"));
            can_1_0 = ImageIO.read(new File("images\\can\\1.0.png"));
            can_0_5 = ImageIO.read(new File("images\\can\\0.5.png"));
            can = can_3_0;
            //System.out.println("resimler yüklendi");

        } catch (IOException ex) {
            System.out.println("resim yukle fonksiyonu resimleri yerınde bulamıyor...");
            Logger.getLogger(StarWarsOyun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void karakter_olustur() {
        //iyi karakter olusturma
        if (karakter.get(0).getKarakter_turu().equals("YODA")) {
            //System.out.println("yoda nesnesi olusturuldu");
            iyi_karakter = new MasterYoda("YODA", "iyi", L);
            iyi_icon = YODA_icon;

        } else if (karakter.get(0).getKarakter_turu().equals("skaywalker")) {
            //System.out.println("skaywalker nesnesi olusturuldu");
            iyi_karakter = new LukeSkywalker("skaywalker", "iyi", L);
            iyi_icon = skywalker_icon;
        }

        //kotu karakterin ilk konumlarını almak
        Start_Kotu1_x = karakter.get(1).getKonum().getX();
        Start_Kotu1_Y = karakter.get(1).getKonum().getY();
        if(karakter.size()== 3){
        Start_Kotu2_x = karakter.get(2).getKonum().getX();
        Start_Kotu2_Y = karakter.get(2).getKonum().getY();
        }
        
        //kötü1 karakterini olusturma
        if (karakter.get(1).getKarakter_turu().equals("Stormtrooper")) {
            //System.out.println("Stormtrooper nesnesi olusturuldu " + "konumu:" + karakter.get(1).getKonum());
            kotu1_karakter = new Stormtrooper("Stormtrooper", "kötü", karakter.get(1).getKonum());
            kotu1_icon = Stormtrooper_icon;
        }
        if (karakter.get(1).getKarakter_turu().equals("KyloRen")) {
            //System.out.println("KyloRen nesnesi olusturuldu" + "konumu:" + karakter.get(1).getKonum());
            kotu1_karakter = new KyloRen("KyloRen", "kötü", karakter.get(1).getKonum());
            kotu1_icon = KyloRen_icon;
        }
        if (karakter.get(1).getKarakter_turu().equals("DarthVader")) {
            // System.out.println("DarthVader nesnesi olusturuldu" + "konumu:" + karakter.get(1).getKonum());
            kotu1_karakter = new DarthVader("DarthVader", "kötü", karakter.get(1).getKonum());
            kotu1_icon = darthVader_icon;
        }

        //kötü2 karakterını olusturma
        if(karakter.size()== 3){
            if (karakter.get(2).getKarakter_turu().equals("Stormtrooper")) {
            // System.out.println("Stormtrooper nesnesi olusturuldu " + "konumu:" + karakter.get(2).getKonum());
            kotu2_karakter = new Stormtrooper("Stormtrooper", "kötü", karakter.get(2).getKonum());
            kotu2_icon = Stormtrooper_icon;
        }
        if (karakter.get(2).getKarakter_turu().equals("KyloRen")) {
            System.out.println("KyloRen olusturuldu");
            //System.out.println("KyloRen nesnesi olusturuldu" + "konumu:" + karakter.get(2).getKonum());
            kotu2_karakter = new KyloRen("KyloRen", "kötü", karakter.get(2).getKonum());
            kotu2_icon = KyloRen_icon;
        }
        if (karakter.get(2).getKarakter_turu().equals("DarthVader")) {
            //System.out.println("DarthVader nesnesi olusturuldu" + "konumu:" + karakter.get(2).getKonum());
            kotu2_karakter = new DarthVader("DarthVader", "kötü", karakter.get(2).getKonum());
            kotu2_icon = darthVader_icon;
        }
        }
        

        //   System.out.println(karakter.get(0).getKarakter_turu());
        //   System.out.println(karakter.get(1).getKonum().getX());
    }

    public StarWarsOyun() {
        
        window = new JFrame();
        window.setSize(800, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.white);
        window.setLayout(null);
        window.setVisible(false);

        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(180, 10, 400, 30);
        titleNamePanel.setBackground(Color.white);
        titleNameLabel = new JLabel("CHOOSE YOUR HERO");
        titleNameLabel.setForeground(Color.black);
        titleNameLabel.setFont(titleFont);

        YODAbuttonPanel = new JPanel();
        YODAbuttonPanel.setBounds(20, 100, 350, 350);
        YODAbuttonPanel.setBackground(Color.white);

        SKYWALKERbuttonPanel = new JPanel();
        SKYWALKERbuttonPanel.setBounds(400, 100, 350, 350);
        SKYWALKERbuttonPanel.setBackground(Color.white);

        YODAIcon = new ImageIcon("images\\icons\\YODA.png");
        YODAbutton = new JButton(YODAIcon);
        YODAbutton.setBackground(Color.white);
        YODAbutton.addActionListener(YODA_tik);
        // YODAbutton.setForeground(Color.white);
        // YODAbutton.setFont(titleFont);

        SKYWALKERIcon = new ImageIcon("images\\icons\\luke skywalker.png");
        SKYWALKERbutton = new JButton(SKYWALKERIcon);
        SKYWALKERbutton.setBackground(Color.white);
        SKYWALKERbutton.addActionListener(SKAYWALKER_tik2);
        // SKYWALKERbutton.setForeground(Color.white);
        // SKYWALKERbutton.setFont(titleFont);

        //adds
        titleNamePanel.add(titleNameLabel);
        YODAbuttonPanel.add(YODAbutton);
        SKYWALKERbuttonPanel.add(SKYWALKERbutton);
        con.add(YODAbuttonPanel);
        con.add(SKYWALKERbuttonPanel);
        con.add(titleNamePanel);

        window.setVisible(true);

    }

    public void TextOkuyucu() {

        //System.out.println("TextOkuyucudayız");
        try (Scanner scn = new Scanner(new BufferedReader(new FileReader("Harita.txt")))) {
            while (scn.hasNextLine()) {
                String satir = scn.nextLine();
                String[] kelime = satir.split(",");
                /* 
                for(int i=0;i<kelime.length;i++){
                    System.out.println(kelime[i]);
                }*/
                //
                if (kelime[0].equalsIgnoreCase("Karakter:Stormtrooper")) {
                    //karakter.add("Stormtrooper");
                    if (kelime[1].equalsIgnoreCase("Kapi:A")) {
                        karakter.add(new karakterListesi("Stormtrooper", A));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:B")) {
                        karakter.add(new karakterListesi("Stormtrooper", B));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:C")) {
                        karakter.add(new karakterListesi("Stormtrooper", C));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:D")) {
                        karakter.add(new karakterListesi("Stormtrooper", D));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:E")) {
                        karakter.add(new karakterListesi("Stormtrooper", E));
                    }
                } else if (kelime[0].equalsIgnoreCase("Karakter:KyloRen")) {
                    //karakter.add("KyloRen");
                    if (kelime[1].equalsIgnoreCase("Kapi:A")) {
                        karakter.add(new karakterListesi("KyloRen", A));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:B")) {
                        karakter.add(new karakterListesi("KyloRen", B));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:C")) {
                        karakter.add(new karakterListesi("KyloRen", C));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:D")) {
                        karakter.add(new karakterListesi("KyloRen", D));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:E")) {
                        karakter.add(new karakterListesi("KyloRen", E));
                    }
                } else if (kelime[0].equalsIgnoreCase("Karakter:DarthVader")) {
                    //karakter.add("DarthVader");
                    if (kelime[1].equalsIgnoreCase("Kapi:A")) {
                        karakter.add(new karakterListesi("DarthVader", A));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:B")) {
                        karakter.add(new karakterListesi("DarthVader", B));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:C")) {
                        karakter.add(new karakterListesi("DarthVader", C));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:D")) {
                        karakter.add(new karakterListesi("DarthVader", D));
                    }
                    if (kelime[1].equalsIgnoreCase("Kapi:E")) {
                        karakter.add(new karakterListesi("DarthVader", E));
                    }
                } else {
                    String Matris[] = satir.split("\t");

                    for (int i = 0; i < Matris.length; i++) {
                        harita.add(Matris[i]);
                    }

                    //elimizde matriside dursun :D
                    // Harita=kelime;
                }
            }//while
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı.....");
        }

        harıtayı_matrise_cevir();
    }

    public class YodaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("YODA SEÇİLDİ");
            karakter.add(0, new karakterListesi("YODA", E));
            resimleri_yukle();
            karakter_olustur();
            oyna();
        }
    }

    public class SkaywlakerAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SKAYWALKER SEÇİLDİ");
            karakter.add(0, new karakterListesi("skaywalker", E));
            resimleri_yukle();
            karakter_olustur();
            oyna();
        }

    }

    public void can_azalt() {
        //canı güncelleme

        if (iyi_karakter.getAd().equalsIgnoreCase("YODA")) {
            iyi_karakter.setCan(iyi_karakter.getCan() - ((float) 0.5));
        } else {
            iyi_karakter.setCan(iyi_karakter.getCan() - ((float) 1.0));
        }

        //resimi güncelleme
        if ((iyi_karakter.getCan()) == ((float) 2.5)) {
            can = can_2_5;
        }
        if ((iyi_karakter.getCan()) == ((float) 2.0)) {
            can = can_2_0;
        }

        if ((iyi_karakter.getCan()) == ((float) 1.5)) {
            can = can_1_5;
        }

        if ((iyi_karakter.getCan()) == ((float) 1.0)) {
            can = can_1_0;
        }
        if ((iyi_karakter.getCan()) == ((float) 0.5)) {
            can = can_0_5;
        }
    }

    public void oyna() {
        En_kısa_yol();
        repaint();

        window.setVisible(true);
        window.remove(titleNamePanel);
        window.remove(YODAbuttonPanel);
        window.remove(SKYWALKERbuttonPanel);
        //focus at
        window.requestFocus();
        window.addKeyListener(girdi);
        window.setFocusable(true);
        window.setFocusTraversalKeysEnabled(false);
        window.setVisible(true);

    }

    public class klavye_girdisi implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //k1 hareket ettirmek
            int b = e.getKeyCode();
            if (b == KeyEvent.VK_LEFT) {
                if (yol_var_mi(iyi_karakter.getYol(), 3)) {
                    // System.out.println("sola bastın");
                    iyi_karakter.getYol().setX(iyi_karakter.getYol().getX() - 1);
                    if (yakalandı_mı()) {
                        Reset();
                    } else {
                        if (kotu1_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                            if (k1path.size() > 3) {
                                k1konum_guncelle(2);

                            } else {
                                Reset();
                            }

                        } else {
                            k1konum_guncelle(1);

                        }
                        //k2 hareket ettirmek
                        if (kotu2_karakter != null) {
                            if (kotu2_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                                if (k2path.size() > 3) {
                                    k2konum_guncelle(2);
                                } else {
                                    Reset();
                                }

                            } else {
                                if (k1path.size() > 3) {
                                    k2konum_guncelle(1);
                                }

                            }
                        }

                    }
                }
            }
            if (b == KeyEvent.VK_RIGHT) {
                if (yol_var_mi(iyi_karakter.getYol(), 4)) {
                    //System.out.println("sağa bastın");
                    iyi_karakter.getYol().setX(iyi_karakter.getYol().getX() + 1);
                    if (yakalandı_mı()) {
                        Reset();
                    } else {
                        if (kotu1_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                            if (k1path.size() > 3) {
                                k1konum_guncelle(2);
                            } else {
                                Reset();
                            }

                        } else {
                            k1konum_guncelle(1);

                        }
                        //k2 hareket ettirmek
                        if (kotu2_karakter != null) {
                            if (kotu2_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                                if (k2path.size() > 3) {
                                    k2konum_guncelle(2);
                                } else {
                                    Reset();
                                }

                            } else {
                                if (k1path.size() > 3) {
                                    k2konum_guncelle(1);
                                }

                            }
                        }

                    }
                }
            }
            if (b == KeyEvent.VK_UP) {
                if (yol_var_mi(iyi_karakter.getYol(), 1)) {
                    //System.out.println("yukarı bastın");
                    iyi_karakter.getYol().setY(iyi_karakter.getYol().getY() - 1);
                    if (yakalandı_mı()) {
                        Reset();
                    } else {
                        if (kotu1_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                            if (k1path.size() > 3) {
                                k1konum_guncelle(2);

                            } else {
                                Reset();
                            }

                        } else {
                            k1konum_guncelle(1);

                        }
                        //k2 hareket ettirmek
                        if (kotu2_karakter != null) {
                            if (kotu2_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                                if (k2path.size() > 3) {
                                    k2konum_guncelle(2);
                                } else {
                                    Reset();
                                }

                            } else {
                                if (k1path.size() > 3) {
                                    k2konum_guncelle(1);
                                }

                            }
                        }

                    }

                }
            }
            if (b == KeyEvent.VK_DOWN) {
                if (yol_var_mi(iyi_karakter.getYol(), 2)) {
                    //System.out.println("aşşagı bastın");
                    iyi_karakter.getYol().setY(iyi_karakter.getYol().getY() + 1);
                    if (yakalandı_mı()) {
                        Reset();
                    } else {
                        if (kotu1_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                            if (k1path.size() > 3) {
                                k1konum_guncelle(2);
                            } else {
                                Reset();
                            }

                        } else {
                            k1konum_guncelle(1);

                        }
                        //k2 hareket ettirmek
                        if (kotu2_karakter != null) {
                            if (kotu2_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                                if (k2path.size() > 3) {
                                    k2konum_guncelle(2);
                                } else {
                                    Reset();
                                }

                            } else {
                                if (k1path.size() > 3) {
                                    k2konum_guncelle(1);
                                }

                            }
                        }

                    }
                }
            }
            if (b == KeyEvent.VK_CONTROL) {
                if (kotu1_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                    if (k1path.size() > 3) {
                        k1konum_guncelle(2);
                    } else {
                        Reset();
                    }

                } else {
                    k1konum_guncelle(1);

                }
                //k2 hareket ettirmek
                if (kotu2_karakter != null) {
                    if (kotu2_karakter.getAd().equalsIgnoreCase("KyloRen")) {
                        if (k2path.size() > 3) {
                            k2konum_guncelle(2);
                        } else {
                            Reset();
                        }

                    } else {
                        if (k1path.size() > 3) {
                            k2konum_guncelle(1);
                        }
                    }
                }

            }

            //yakalandı mı veya bitti mi?
            if (yakalandı_mı()) {
                Reset();
            }

            En_kısa_yol();
            repaint();

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    public void harıtayı_matrise_cevir() {
        int x = 0, y = 0;
        for (String a : harita) {
            HaritaMatris[y][x] = a;
            x++;
            if (x % 14 == 0) {
                x = 0;
                y++;
            }
        }
    }

    public boolean yol_var_mi(Lokasyon karakater_konumu, int x) {
        //x yön     1->yukarı    2->aşşağı    3->sol     4->sağ

        if (x == 1) {
            //harita dısına çıkamaz
            if (karakater_konumu.getY() - 1 == -1) {
                return false;
            }
            if (HaritaMatris[karakater_konumu.getY() - 1][karakater_konumu.getX()].equalsIgnoreCase("1")) {
                return true;
            }
        }
        if (x == 2) {
            //harita dısına çıkamaz
            if (karakater_konumu.getY() + 1 == 11) {
                return false;
            }
            if (HaritaMatris[karakater_konumu.getY() + 1][karakater_konumu.getX()].equalsIgnoreCase("1")) {
                return true;
            }

        }
        if (x == 3) {
            if (karakater_konumu.getX() - 1 == -1) {
                return false;
            }
            if (HaritaMatris[karakater_konumu.getY()][karakater_konumu.getX() - 1].equalsIgnoreCase("1")) {
                return true;
            }
        }
        if (x == 4) {
            if (karakater_konumu.getX() + 1 == 14 && karakater_konumu.getY() == 9) {
                //kazandınız 
                String message = "kazandınız";
                JOptionPane.showMessageDialog(this, message);
                System.exit(0);
            } else if (karakater_konumu.getX() + 1 == 14) {
                return false;
            } else if (HaritaMatris[karakater_konumu.getY()][karakater_konumu.getX() + 1].equalsIgnoreCase("1")) {
                return true;
            }
        }

        return false;
    }

    public boolean yakalandı_mı() {

        if (iyi_karakter.getYol().getX() == kotu1_karakter.getYol().getX() && iyi_karakter.getYol().getY() == kotu1_karakter.getYol().getY()) {
            System.out.println("kötü 1 e yakalandı");
            return true;
        }
        if(kotu2_karakter!=null){
            if (iyi_karakter.getYol().getX() == kotu2_karakter.getYol().getX() && iyi_karakter.getYol().getY() == kotu2_karakter.getYol().getY()) {
            System.out.println("kötü 2 ye yakalandı");
            return true;
        }
        }
        
        return false;
    }

    public void Reset() {
        can_azalt();
        //kaybettiyse
        if (iyi_karakter.getCan() == (float) 0.0) {
            String message = "KAYBETTİN";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);

        }
        iyi_karakter.setYol(new Lokasyon(6, 5));
        kotu1_karakter.setYol(new Lokasyon(Start_Kotu1_x, Start_Kotu1_Y));
        if(kotu2_karakter!=null){
        kotu2_karakter.setYol(new Lokasyon(Start_Kotu2_x, Start_Kotu2_Y));
        }
        

    }

    public void En_kısa_yol() {
        int[][] blocksArray = new int[76][2];
        if (!kotu1_karakter.getAd().equalsIgnoreCase("DarthVader")) {
            blocksArray = duvarlar(blocksArray);
        } else {
            blocksArray = new int[0][0];
        }
        dondur(kotu1_karakter.EnkısaYol(k1path, iyi_karakter.Yol, blocksArray));

        //aynı işlemleri 2. kotuyede uygula  
        if (kotu2_karakter != null) {
            int[][] block2sArray = new int[76][2];
            if (!kotu2_karakter.getAd().equalsIgnoreCase("DarthVader")) {
                block2sArray = duvarlar(block2sArray);
            } else {
                block2sArray = new int[0][0];
            }
            dondur2(kotu2_karakter.EnkısaYol(k2path, iyi_karakter.Yol, block2sArray));
        }

    }

    public List<Dugum> dondur(List<Dugum> d) {
        return k1path = d;
    }

    public List<Dugum> dondur2(List<Dugum> e) {
        return k2path = e;
    }

    public void k1konum_guncelle(int indis) {
        kotu1_karakter.getYol().setX(k1path.get(indis).getSatır_sayısı());
        kotu1_karakter.getYol().setY(k1path.get(indis).getStun_sayısı());
    }

    public void k2konum_guncelle(int indis) {
        kotu2_karakter.getYol().setX(k2path.get(indis).getSatır_sayısı());
        kotu2_karakter.getYol().setY(k2path.get(indis).getStun_sayısı());
    }

    public int[][] duvarlar(int[][] blocksArray) {
        int x = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 14; j++) {
                if (HaritaMatris[i][j].equalsIgnoreCase("0")) {
                    blocksArray[x][0] = j;
                    blocksArray[x][1] = i;
                    x++;
                }

            }
        }
        return blocksArray;
    }

    public boolean k1k() {
        if (k1path.get(1).getSatır_sayısı() == kotu1_karakter.getYol().getX() && k1path.get(1).getStun_sayısı() == kotu1_karakter.getYol().getY()) {
            return true;
        }
        /*
        if(k1path.get(2).getSatır_sayısı()==kotu1_karakter.getYol().getX()&&k1path.get(2).getStun_sayısı()==kotu1_karakter.getYol().getY()){
           return true;
        } 
         */
        return false;
    }

    public boolean k2k() {
        if (k2path.get(1).getSatır_sayısı() == kotu2_karakter.getYol().getX() && k2path.get(1).getStun_sayısı() == kotu2_karakter.getYol().getY()) {
            return true;
        }
        /* 
        if(k2path.get(2).getSatır_sayısı()==kotu2_karakter.getYol().getX()&&k2path.get(2).getStun_sayısı()==kotu2_karakter.getYol().getY()){
           return true;
        }  
         */
        return false;
    }

}
