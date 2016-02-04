package com.kodcu.state;

public class Tarti {
 
	final static int JETON_VAR = 0;
	final static int JETON_YOK = 1;
    final static int OLCME_TAMAM = 2;
    final static int ISLEM_TAMAM = 3;
 
	int state = JETON_YOK;

  
	public Tarti() {
		System.out.println("Tarti sinifi olusturuldu...");
	}
  
	public void jetonAt() { // action
		if (state == JETON_VAR) {
			System.out.println("Zaten jeton attiniz ");
		} else if (state == JETON_YOK) {
			// Jeton attiysa ve jeton yoksa bu istedigimiz durum
            state = JETON_VAR;
			System.out.println("Tesekkurler, islem birazdan baslayacak lutfen hareketsiz durunuz...");
		} else if (state == ISLEM_TAMAM) {
        	System.out.println("Surec henuz baslamadi, ne alakasi var simdi...");
		} else if (state == OLCME_TAMAM) {
            System.out.println("Jeton atmadan olcum yapilamaz...");
        }
	}


    public void olc() { // action
        if (state == JETON_VAR) {
            // Jeton tamam site olcemede
            state = OLCME_TAMAM;
            System.out.println("Olcumunuz yapildi, sonuclar için lutfen bekleyin...");
        } else if (state == JETON_YOK) {
            System.out.println("Lutfen Jeton atiniz...");
        } else if (state == ISLEM_TAMAM) {
            System.out.println("Surec henuz baslamadi, ne alakasi var simdi...");
        } else if (state == OLCME_TAMAM) {
            System.out.println("Bu asamada olcum yapalimaz...");
        }

    }
 
	public void bilgileriVer() { // action
        if (state == JETON_VAR) {
            System.out.println("Zaten jeton attiniz ");
        } else if (state == JETON_YOK) {
            System.out.println("Lutfen Jeton atiniz...");
        } else if (state == ISLEM_TAMAM) {
            System.out.println("Henuz olcum yapilmadi...");
        } else if (state == OLCME_TAMAM) {
            state = JETON_YOK;
            System.out.println("Sonuclariniz hazir. Bizi tercih ettiginiz icin tesekkurler");
        }
	}
 

 


	public String toString() {
		StringBuffer sonuc = new StringBuffer();


		sonuc.append("\n");
		return sonuc.toString();
	}
}


